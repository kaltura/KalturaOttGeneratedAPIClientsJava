package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static com.kaltura.client.test.Properties.*;

public class PerformanceAppLogUtils extends BaseUtils {

    /**
     * class to save data about count of slow executions and count of total executions for methods checking in regression
     */
    @Data
    static class SlowRatio {
        private int slowCount;
        private int totalCount;
    }

    private static final int maxAllowedPercentage = Integer.valueOf(getProperty(MAX_ALLOWED_PERCENTAGE));
    private static final String domain = getProperty(PHOENIX_SERVER_DOMAIN_NAME);
    private static final String userName = getProperty(PHOENIX_SERVER_USER_NAME);
    private static final String password = getProperty(PHOENIX_SERVER_PASSWORD);
    private static final String remoteSourceFileDir = getProperty(PHOENIX_SERVER_LOGS_DIRECTORY) + getProperty(API_VERSION) + "/";
    private static final String remoteSourceUrlFileDir = getProperty(PHOENIX_SERVER_LOGS_DIRECTORY_URL) + getProperty(API_VERSION) + "\\";
    private static final UserAuthenticator auth = new StaticUserAuthenticator(domain, userName, password);
    private static final FileSystemOptions options = new FileSystemOptions();

    private static final String COUCHBASE_LOG_DATA = "\"e\":\"cb\"";
    private static final String DB_LOG_DATA = "\"e\":\"db\"";
    private static final String ELASTIC_SEARCH_LOG_DATA = "\"e\":\"es\"";
    private static final String RABBIT_LOG_DATA = "\"e\":\"rabbit\"";

    private static List<String> appLogLocalFileNames = new ArrayList<>();

    private static final List<String> nonRelated2CodeStringsList = new ArrayList<String>() {{
        add("\"e\":\"start_api\"");
        add(COUCHBASE_LOG_DATA);
        add(DB_LOG_DATA);
        add(ELASTIC_SEARCH_LOG_DATA);
        add(RABBIT_LOG_DATA);
    }};

    private static double timeOfCode;
    private static double timeOfCB;
    private static double timeOfDB;
    private static double timeOfES;
    private static double timeOfRabbit;
    private static double totalTime;
    private static boolean isKalturaSessionFoundInAppLogFile;
    private static int countOfCB;
    private static int countOfDB;
    private static int countOfES;
    private static int countOfRabbit;

    public static void createPerformanceCodeReport() {
        try {
            // copy log files to local machine
            List<String> appRemoteFileNames = getRemoteAppLogFileNames();
            for (String fileName : appRemoteFileNames) {
                copyRemoteFile2LocalMachine(fileName);
            }

            // aggregated last regression results
            Map<String, List<String>> methodsAndKalturaSessions = loadMethodsAndSessionsFromTestFile();

            Map<String, SlowRatio> methodsAndSlowRatioData = new HashMap<>();
            SlowRatio slowRatio;
            for (String method : methodsAndKalturaSessions.keySet()) {
                methodsAndSlowRatioData.put(method, new SlowRatio());
                //Logger.getLogger(PerformanceAppLogUtils.class).debug("Method: [" + method + "]");
                for (String xKalturaSession : methodsAndKalturaSessions.get(method)) {
                    slowRatio = methodsAndSlowRatioData.get(method);
                    slowRatio.totalCount++;
                    methodsAndSlowRatioData.put(method, slowRatio);
                    //Logger.getLogger(PerformanceAppLogUtils.class).debug("xKalturaSession: [" + xKalturaSession + "]");
                    for (String appFileName : appRemoteFileNames) {
                        isKalturaSessionFoundInAppLogFile = false;
                        timeOfCode = 0.0;
                        timeOfCB = 0.0;
                        timeOfDB = 0.0;
                        timeOfES = 0.0;
                        timeOfRabbit = 0.0;
                        totalTime = 0.0;

                        countOfCB = 0;
                        countOfDB = 0;
                        countOfES = 0;
                        countOfRabbit = 0;

                        calcTimeExecution(appFileName, xKalturaSession);

                        if (isKalturaSessionFoundInAppLogFile) {
                            double percentageCodeTime2TotalTime = timeOfCode / totalTime * 100;

                            // include in report only relevant cases
                            if (percentageCodeTime2TotalTime > maxAllowedPercentage &&
                                    totalTime > Double.parseDouble(getProperty(MAX_ALLOWED_EXECUTION_TIME_IN_SEC))) {
                                slowRatio = methodsAndSlowRatioData.get(method);
                                slowRatio.slowCount++;
                                methodsAndSlowRatioData.put(method, slowRatio);

                                // save results
                                writeReport2File(method, xKalturaSession, percentageCodeTime2TotalTime);
                            }
                        }
                    }
                }
            }
            addSummary2Report(methodsAndSlowRatioData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addSummary2Report(Map<String, SlowRatio> methodsAndSlowRatioData) throws IOException {
        Logger.getLogger(PerformanceAppLogUtils.class).debug("addSummary2Report started");
        if (methodsAndSlowRatioData.keySet().size() > 0) {
            String reportFileName = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) +
                    getProperty(CODE_PERFORMANCE_REPORT_FILE);
            String summaryTemporaryFileName = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + "SUMMARY" +
                    getProperty(CODE_PERFORMANCE_REPORT_FILE);

            createSummaryFile(methodsAndSlowRatioData, summaryTemporaryFileName);
            addReportDataIntoSummaryFile(reportFileName, summaryTemporaryFileName);

            File source = new File(summaryTemporaryFileName);
            File target = new File(reportFileName);
            deleteFile(reportFileName);
            boolean success = source.renameTo(target);
            if (!success) {
                throw new IOException("File can't be renamed");
            }
            Logger.getLogger(PerformanceAppLogUtils.class).debug("Report was successfully created: [" + reportFileName + "]");
        }
        Logger.getLogger(PerformanceAppLogUtils.class).debug("addSummary2Report completed");
    }

    private static void addReportDataIntoSummaryFile(String fromFile, String toFile) {
        Logger.getLogger(PerformanceAppLogUtils.class).debug("addReportDataIntoSummaryFile started");
        Logger.getLogger(PerformanceAppLogUtils.class).debug(Paths.get(fromFile));
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fromFile));
             FileWriter fw = new FileWriter(toFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            Stream<String> lines = br.lines();
            lines.forEach(out::println);
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Logger.getLogger(PerformanceAppLogUtils.class).debug("addReportDataIntoSummaryFile completed");
        }
    }

    private static void createSummaryFile(Map<String, SlowRatio> methodsAndSlowRatioData, String summaryTemporaryFileName) {
        Logger.getLogger(PerformanceAppLogUtils.class).debug("createSummaryFile started");
        try (FileWriter fw = new FileWriter(summaryTemporaryFileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Report of slow methods on " + getCurrentDateInFormat("dd/MM/yyyy HH:mm") + " (" + getProperty(API_VERSION) + ")");
            out.println("Max allowed percentage: " + getProperty(MAX_ALLOWED_PERCENTAGE));
            out.println("Max allowed execution time in seconds: " + getProperty(MAX_ALLOWED_EXECUTION_TIME_IN_SEC));
            out.println();
            out.println("Summary of slow methods are below:");
            out.println();
            for (String method : methodsAndSlowRatioData.keySet()) {
                if (methodsAndSlowRatioData.get(method).slowCount > 0) {
                    out.println(method + " was slow " + String.format("%.2f", methodsAndSlowRatioData.get(method).slowCount *
                            1.0 / methodsAndSlowRatioData.get(method).totalCount * 100) + "% of executions (" +
                            methodsAndSlowRatioData.get(method).slowCount + "/" + methodsAndSlowRatioData.get(method).totalCount + ")");
                }
            }

            out.println();
            out.println("Details of slow methods are below:");
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Logger.getLogger(PerformanceAppLogUtils.class).debug("createSummaryFile completed");
        }
    }

    private static void writeReport2File(String method, String xKalturaSession, double codeTimePercentage) {
        try (FileWriter fw = new FileWriter(getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) +
                getProperty(CODE_PERFORMANCE_REPORT_FILE), true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // we want to see only data where code time is less than 100%
            if (timeOfCB > 0 || timeOfDB > 0 || timeOfES > 0 || timeOfRabbit > 0) {
                out.println(method);
                out.println(xKalturaSession);
                out.println("Execution Time: " + String.format("%.3f", totalTime));
                out.println("Code: " + String.format("%.2f", codeTimePercentage) + "% (" + String.format("%.3f", timeOfCode) + ")");
                writeIfValueMoreThanZero(out, "Couchbase: ", timeOfCB, totalTime, countOfCB);
                writeIfValueMoreThanZero(out, "DB: ", timeOfDB, totalTime, countOfDB);
                writeIfValueMoreThanZero(out, "Elastic: ", timeOfES, totalTime, countOfES);
                writeIfValueMoreThanZero(out, "Rabbit: ", timeOfRabbit, totalTime, countOfRabbit);
                out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeIfValueMoreThanZero(PrintWriter out, String title, double timeOfEvent, double totalTime, int countOfEvent) {
        if (timeOfEvent > 0) {
            out.println(title + " " + String.format("%.2f", timeOfEvent / totalTime * 100) + "% (" + String.format("%.3f", timeOfEvent) + ") [" + countOfEvent + " queries]");
        }
    }

    private static void copyRemoteFile2LocalMachine(String remoteFileName) throws IOException {
        Logger.getLogger(PerformanceAppLogUtils.class).debug("copyRemoteFile2LocalMachine() with [" + remoteFileName + "]: started");

        // add local target folder in case it does not exist
        File localTargetFolderPath = new File(getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH));
        if (!localTargetFolderPath.exists()) {
            boolean isDirCreated = localTargetFolderPath.mkdir();
            Logger.getLogger(PerformanceAppLogUtils.class).debug("Directory [" +
                    getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + "] was created == [" + isDirCreated + "]");
        }

        String remoteFilePathUrl = remoteSourceUrlFileDir + remoteFileName;
        String localTargetFilePath = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + "copied-" + remoteFileName;
        appLogLocalFileNames.add(localTargetFilePath);

        // remove local target file in case it exists and create it empty
        File targetFile = new File(localTargetFilePath);
        if (targetFile.exists()) {
            targetFile.delete();
        }
        targetFile.createNewFile();
        //FileObject destination = VFS.getManager().resolveFile(targetFile.getAbsolutePath());

        //domain, username, password
        //DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(options, auth);

        //FileObject fileObject = VFS.getManager().resolveFile(remoteFilePath, options);

        // copy file from remote to local folder
//        if (fileObject.exists()) {
//            destination.copyFrom(fileObject, Selectors.SELECT_SELF);
//        }
        if (isUrlExists(remoteFilePathUrl)) {
            FileUtils.copyURLToFile(new URL(remoteFilePathUrl), targetFile);
        }
        //destination.close();
        Logger.getLogger(PerformanceAppLogUtils.class).debug("File [" + remoteFilePathUrl + "] was copied into [" + localTargetFilePath + "]");
        Logger.getLogger(PerformanceAppLogUtils.class).debug("copyRemoteFile2LocalMachine(): completed");
    }

    public static List<String> getRemoteAppLogFileNames() throws IOException {
        Logger.getLogger(PerformanceAppLogUtils.class).debug("getRemoteAppLogFileNames(): started");
        List<String> fileNames = new ArrayList<>();
        String sourceFileName = getProperty(PHOENIX_SERVER_LOG_FILE_NAME_PREFIX) + getProperty(API_VERSION) +
                getProperty(PHOENIX_SERVER_LOG_FILE_EXTENSION);
        String remoteFilePathUrl = remoteSourceUrlFileDir + sourceFileName;
        Logger.getLogger(PerformanceAppLogUtils.class).debug("remoteFilePathUrl: " + remoteFilePathUrl);

//        //domain, username, password
//        DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(options, auth);
//
//        FileObject fileObject = VFS.getManager().resolveFile(remoteFilePathUrl, options);

//        if (fileObject.exists()) {
//            fileNames.add(sourceFileName);
//        } else {
//            Logger.getLogger(PerformanceAppLogUtils.class).error("getRemoteAppLogFileNames(): file not found!");
//        }
        if (isUrlExists(remoteFilePathUrl)) {
            fileNames.add(sourceFileName);
        } else {
            Logger.getLogger(PerformanceAppLogUtils.class).error("getRemoteAppLogFileNames(): file not found!");
        }
        int idx = 1;
        while (isUrlExists(remoteFilePathUrl)) {
            // all files related needed logs have the same name as value from sourceFileName and additionally they have
            // suffixes that looks like ".1", ".2", etc...
            String name = sourceFileName + "." + idx;
            //fileObject = VFS.getManager().resolveFile(remoteSourceFileDir + name, options);
            idx++;
            // sometimes file can be removed and it means we should one more time check names
            if (isUrlExists(remoteSourceUrlFileDir + name)) {
                fileNames.add(name);
            } else {
                // to handle case when next file has difference in suffixes bigger than 1
                name = sourceFileName + "." + idx;
                idx++;
                if (isUrlExists(remoteSourceUrlFileDir + name)) {
                    fileNames.add(name);
                }
            }
        }
        Logger.getLogger(PerformanceAppLogUtils.class).debug("getRemoteAppLogFileNames(): completed");
        return fileNames;
    }

    /*If the connection to a URL (made with HttpURLConnection) returns with HTTP status code 200 then the file exists.
        Since we only care it exists or not there is no need to request the entire document.
        We can just request the header using the HTTP HEAD request method to check if it exists.*/
    private static boolean isUrlExists(String fileUrl) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con = (HttpURLConnection) new URL(fileUrl).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * We have file with name getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + getProperty(REGRESSION_LOGS_LOCAL_FILE)
     * that saves information about all executed methods and their kaltura sessions.
     * That file will be filled with data only in case test.properties has should_regression_logs_be_saved=true
     *
     * @return map contains all methods and kaltura sessions related to regression
     */
    private static Map<String, List<String>> loadMethodsAndSessionsFromTestFile() {
        Logger.getLogger(PerformanceAppLogUtils.class).debug("loadMethodsAndSessionsFromTestFile(): started");
        Map<String, List<String>> result = new HashMap<>();
        String[] values;
        try {
            InputStream is = new FileInputStream(getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) +
                    getProperty(REGRESSION_LOGS_LOCAL_FILE));
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line;
            List<String> sessions;
            while ((line = buf.readLine()) != null) {
                values = line.split(" ");
                sessions = result.get(values[0]);
                if (sessions == null) {
                    sessions = new ArrayList<>();
                }
                sessions.add(values[1]);
                result.put(values[0], sessions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Logger.getLogger(PerformanceAppLogUtils.class).debug("loadMethodsAndSessionsFromTestFile(): completed");
            return result;
        }
    }

    private static void calcTimeExecution(String appFileName, String kalturaSession) {
        String path2File = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + "copied-" + appFileName;

        String[] nonRelated2CodeStringsArray = new String[nonRelated2CodeStringsList.size()];
        String executionTimeString;
        try (BufferedReader br = new BufferedReader(new FileReader(path2File))) {
            for (String line; (line = br.readLine()) != null; ) {
                if (line.contains(kalturaSession)) {
                    isKalturaSessionFoundInAppLogFile = true;
                    // this is a usual position of time in the whole string
                    executionTimeString = line.split("\"")[3];
                    // "e": "ws" should be ignored as it partially described in other events
                    if (stringContainsItemFromArray(line, nonRelated2CodeStringsList.toArray(nonRelated2CodeStringsArray))) {
                        timeOfCode = timeOfCode - Double.valueOf(executionTimeString);
                    } else {
                        if (line.contains("\"e\":\"end_api\"")) {
                            totalTime = Double.valueOf(executionTimeString);
                            timeOfCode = timeOfCode + totalTime;
                        }
                    }
                    if (line.contains(COUCHBASE_LOG_DATA)) {
                        timeOfCB = timeOfCB + Double.valueOf(executionTimeString);
                        countOfCB++;
                    }
                    if (line.contains(DB_LOG_DATA)) {
                        timeOfDB = timeOfDB + Double.valueOf(executionTimeString);
                        countOfDB++;
                    }
                    if (line.contains(ELASTIC_SEARCH_LOG_DATA)) {
                        timeOfES = timeOfES + Double.valueOf(executionTimeString);
                        countOfES++;
                    }
                    if (line.contains(RABBIT_LOG_DATA)) {
                        timeOfRabbit = timeOfRabbit + Double.valueOf(executionTimeString);
                        countOfRabbit++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean stringContainsItemFromArray(String inputStr, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
    }

    /**
     * method removes from local computer copied on it from remote machine app log files
     */
    public static void removeCopiedAppLogFiles() {
        for (String file : appLogLocalFileNames) {
            deleteFile(file);
        }
    }
}