package com.kaltura.client.test.utils;

import com.kaltura.client.Logger;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import java.io.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import static com.kaltura.client.test.Properties.*;
import static com.kaltura.client.test.Properties.API_VERSION;
import static com.kaltura.client.test.Properties.getProperty;

public class PerformanceAppLogUtils extends BaseUtils {

    private static final int maxAllowedPercentage = Integer.valueOf(getProperty(MAX_ALLOWED_PERCENTAGE));
    private static final String domain = getProperty(PHOENIX_SERVER_DOMAIN_NAME);
    private static final String userName = getProperty(PHOENIX_SERVER_USER_NAME);
    private static final String password = getProperty(PHOENIX_SERVER_PASSWORD);
    private static final UserAuthenticator auth = new StaticUserAuthenticator(domain, userName, password);
    private static final FileSystemOptions options = new FileSystemOptions();
    private static final String remoteSourceFileDir = getProperty(PHOENIX_SERVER_LOGS_DIRECTORY) + getProperty(API_VERSION) + "\\";

    private static final String COUCHBASE_LOG_DATA = "\"e\":\"cb\"";
    private static final String DB_LOG_DATA = "\"e\":\"db\"";
    private static final String ELASTIC_SEARCH_LOG_DATA = "\"e\":\"es\"";
    private static final String RABBIT_LOG_DATA = "\"e\":\"rabbit\"";

    private static final List<String> nonRelated2CodeStringsList = new ArrayList<>();
    {
        nonRelated2CodeStringsList.add("\"e\":\"start_api\"");
        nonRelated2CodeStringsList.add(COUCHBASE_LOG_DATA);
        nonRelated2CodeStringsList.add(DB_LOG_DATA);
        nonRelated2CodeStringsList.add(ELASTIC_SEARCH_LOG_DATA);
        nonRelated2CodeStringsList.add(RABBIT_LOG_DATA);
    }

    private static double timeOfCode;
    private static double timeOfCB;
    private static double timeOfDB;
    private static double timeOfES;
    private static double timeOfRabbit;
    private static double totalTime;
    private static boolean isKalturaSessionFoundInFile;

    public static void testPerformanceCode() {
        try {
            List<String> appRemoteFileNames = getRemoteAppLogFileNames();
            for (String fileName: appRemoteFileNames) {
                copyRemoteFile2LocalMachine(fileName);
            }

            Map<String, List<String>> methodsAndKalturaSessions = loadMethodsAndSessionsFromTestFile();

            for (String method: methodsAndKalturaSessions.keySet()) {
                Logger.getLogger(PerformanceAppLogUtils.class).debug("Method: [" + method + "]");
                for (String xKalturaSession: methodsAndKalturaSessions.get(method)) {
                    Logger.getLogger(PerformanceAppLogUtils.class).debug("xKalturaSession: [" + xKalturaSession + "]");
                    for (String appFileName: appRemoteFileNames) {
                        isKalturaSessionFoundInFile = false;
                        timeOfCode = 0.0;
                        timeOfCB = 0.0;
                        timeOfDB = 0.0;
                        timeOfES = 0.0;
                        timeOfRabbit = 0.0;
                        totalTime = 0.0;

                        calcTimeExecution(appFileName, xKalturaSession);

                        if (isKalturaSessionFoundInFile) {
                            // display results
                            double percentageCodeTime2TotalTime = timeOfCode / totalTime * 100;
                            if (percentageCodeTime2TotalTime > maxAllowedPercentage) {
                                // TODO: save report into file and add summary
                                Logger.getLogger(PerformanceAppLogUtils.class).debug("code time = " + timeOfCode + " PERFORMANCE LEVEL IS TOO WEAK: " +
                                        String.format("%.2f", percentageCodeTime2TotalTime) + "%" + " \"" + xKalturaSession + "\"");
                            } /* THAT PART COMMENTED TO GET IT EASY IN CASE IT NEEDED LATEE
                            else {
                                logIfValueMoreThanZero(timeOfCode, "code time = ");
                                logIfValueMoreThanZero(timeOfCB, "Couchbase time = ");
                                logIfValueMoreThanZero(timeOfDB, "Database time = ");
                                logIfValueMoreThanZero(timeOfES, "Elastic search time = ");
                                logIfValueMoreThanZero(timeOfRabbit, "Rabbit time = ");
                            }*/
                        }
                    }
                }
            }

            deleteResultsOfRegressionExecution();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * After execution of util we have to delete log file created during regression execution to not affect results of next checking
     */
    private static void deleteResultsOfRegressionExecution() {
        String fileName = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + getProperty(REGRESSION_LOGS_LOCAL_FILE);
        String prefix = getOffsetDateInFormat(0, "dd.MM.yyyy hh.mm");
        String targetFileName = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + prefix +
                getProperty(REGRESSION_LOGS_LOCAL_FILE);
        try {
            Files.copy(Paths.get(fileName), new FileOutputStream(targetFileName));
            File targetFile = new File(targetFileName);
            if (targetFile.exists()) {
                deleteFile(fileName);
                Logger.getLogger(PerformanceAppLogUtils.class).debug("File: [" + targetFileName + "] has been created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logIfValueMoreThanZero(double time, String message) {
        if (time > 0) {
            Logger.getLogger(PerformanceAppLogUtils.class).debug(message + time);
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

        String remoteFilePath = remoteSourceFileDir + remoteFileName;
        String localTargetFilePath = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + "copied-" + remoteFileName;

        // remove local target file in case it exists and create it empty
        File targetFile = new File(localTargetFilePath);
        if (targetFile.exists()) {
            targetFile.delete();
        }
        targetFile.createNewFile();
        FileObject destination = VFS.getManager().resolveFile(targetFile.getAbsolutePath());

        //domain, username, password
        DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(options, auth);

        FileObject fileObject = VFS.getManager().resolveFile(remoteFilePath, options);

        // copy file from remote to local folder
        if (fileObject.exists()) {
            destination.copyFrom(fileObject, Selectors.SELECT_SELF);
        }
        destination.close();
        Logger.getLogger(PerformanceAppLogUtils.class).debug("File [" + remoteFilePath + "] was copied into [" + localTargetFilePath + "]");

        Logger.getLogger(PerformanceAppLogUtils.class).debug("copyRemoteFile2LocalMachine(): closed");
    }

    private static List<String> getRemoteAppLogFileNames() throws IOException {
        Logger.getLogger(PerformanceAppLogUtils.class).debug("getRemoteAppLogFileNames(): started");
        List<String> fileNames = new ArrayList<>();
        String sourceFileName = getProperty(PHOENIX_SERVER_LOG_FILE_NAME_PREFIX) + getProperty(API_VERSION) +
                getProperty(PHOENIX_SERVER_LOG_FILE_EXTENSION);
        String remoteFilePath = remoteSourceFileDir + sourceFileName;

        //domain, username, password
        DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(options, auth);

        FileObject fileObject = VFS.getManager().resolveFile(remoteFilePath, options);

        if (fileObject.exists()) {
            fileNames.add(sourceFileName);
        }
        int idx = 1;
        while (fileObject.exists()) {
            // all files related needed logs have the same name as value from sourceFileName and additionally they have suffix looks like ".1", ".2", etc
            String name = sourceFileName + "." + idx;
            fileObject = VFS.getManager().resolveFile(remoteSourceFileDir + name, options);
            idx++;
            // sometimes file can be removed and it means we should one more time check names
            if (fileObject.exists()) {
                fileNames.add(name);
            } else {
                name = sourceFileName + "." + idx;
                fileObject = VFS.getManager().resolveFile(remoteSourceFileDir + name, options);
                idx++;
                if (fileObject.exists()) {
                    fileNames.add(name);
                }
            }
        }
        return fileNames;
    }

    /**
     * We have file with name getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + getProperty(REGRESSION_LOGS_LOCAL_FILE)
     * that saves information about all executed methods and their kaltura sessions.
     * That file will be filled with data only in case test.properties has should_regression_logs_be_saved=true
     *
     * @return map contains all methods and kaltura sessions related to regression
     */
    private static Map<String, List<String>> loadMethodsAndSessionsFromTestFile() {
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
            return result;
        }
    }

    private static void calcTimeExecution(String appFileName, String kalturaSession) {
        String path2File = getProperty(PHOENIX_SERVER_LOGS_LOCAL_FOLDER_PATH) + "copied-" + appFileName;

        String[] nonRelated2CodeStringsArray = new String[nonRelated2CodeStringsList.size()];
        String executionTimeString;
        try(BufferedReader br = new BufferedReader(new FileReader(path2File))) {
            for(String line; (line = br.readLine()) != null; ) {
                if (line.contains(kalturaSession)) {
                    isKalturaSessionFoundInFile = true;
                    // this is a usual position of time in the whole string
                    executionTimeString = line.split("\"")[3];
                    // "e": "ws" should be ignored as it partially described in other events
                    if (stringContainsItemFromArray(line, nonRelated2CodeStringsList.toArray(nonRelated2CodeStringsArray))) {
                        timeOfCode = timeOfCode - Double.valueOf(executionTimeString);
                    } else {
                        if (line.contains("\"e\":\"end_api\"")) {
                            //System.out.println(2);
                            totalTime = Double.valueOf(executionTimeString);
                            timeOfCode = timeOfCode + totalTime;
                        }
                    }
                    if (line.contains(COUCHBASE_LOG_DATA)) {
                        timeOfCB = timeOfCB + Double.valueOf(executionTimeString);
                    }
                    if (line.contains(DB_LOG_DATA)) {
                        timeOfDB = timeOfDB + Double.valueOf(executionTimeString);
                    }
                    if (line.contains(ELASTIC_SEARCH_LOG_DATA)) {
                        timeOfES = timeOfES + Double.valueOf(executionTimeString);
                    }
                    if (line.contains(RABBIT_LOG_DATA)) {
                        timeOfRabbit = timeOfRabbit + Double.valueOf(executionTimeString);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean stringContainsItemFromArray(String inputStr, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
    }
}
