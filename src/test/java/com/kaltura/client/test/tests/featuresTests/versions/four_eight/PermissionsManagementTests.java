package com.kaltura.client.test.tests.featuresTests.versions.four_eight;

import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.test.utils.PermissionManagementUtils;
import com.kaltura.client.test.utils.dbUtils.PermissionsManagementDBUtils;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static com.kaltura.client.test.utils.BaseUtils.deleteFile;
import static com.kaltura.client.test.utils.BaseUtils.getFileContent;
import static com.kaltura.client.test.utils.PermissionManagementUtils.executeCommandsInColsole;
import static com.kaltura.client.test.utils.PermissionManagementUtils.getConsoleCommand;
import static com.kaltura.client.test.utils.dbUtils.PermissionsManagementDBUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-4885
 * started in 4_8 and completed in 5_0_3
 */
@Test(groups = {"Permission management"})
public class PermissionsManagementTests {

    String mainFile = "PermissionsDeployment.exe";
    // that file generated automatically
    String path2Log = "C:\\log\\permissions\\permissions.log";
    String path2Util = "C:\\123\\PermissionsExport\\bin\\Debug\\";

    // these files are generated
    String dataFilePath = path2Util + "333\\" + "exp1.txt";
    String path2JsonFolder = path2Util + "333\\JSON\\";
    String generatedDataFilePath = path2Util + "333\\" + "import.txt";
    String path2JsonRoles = path2JsonFolder + "roles.json";
    String path2JsonPermissions = path2JsonFolder + "permissions.json";
    String path2JsonMethods = path2JsonFolder + "permission_items\\controllers\\";
    String fullPath2Util = path2Util + mainFile;

    // these files added into project
    String importOnly4TablesFilePath;
    String path2EmptyFile;

    @BeforeClass
    public void setUp() {
        ClassLoader classLoader = PermissionsManagementTests.class.getClassLoader();
        File file = new File(classLoader.getResource("permission_management_data/empty_file.txt").getFile());
        path2EmptyFile = file.getAbsolutePath();
        file = new File(classLoader.getResource("permission_management_data/importOnly4Tables.txt").getFile());
        importOnly4TablesFilePath = file.getAbsolutePath();
    }

    public static final String EXPORT_KEY = "e=";
    public static final String IMPORT_KEY = "i=";
    public static final String DELETE_KEY = "d=";
    public static final String EXPORT_JSON_KEY = "n=";
    public static final String IMPORT_JSON_KEY = "l=";

    @Severity(SeverityLevel.MINOR)
    @Test(description = "execute console util without parameters")
    public void runningWithoutParameters() {
        List<String> commands = getConsoleCommand(fullPath2Util, "");
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("Permissions deployment tool");
        assertThat(consoleOutput).contains("Shortcut: e");
        assertThat(consoleOutput).contains("Shortcut: i");
        assertThat(consoleOutput).contains("Shortcut: d");
        assertThat(consoleOutput).contains("Shortcut: n");
        assertThat(consoleOutput).contains("Shortcut: l");
    }

    @Severity(SeverityLevel.MINOR)
    @Issue("BEO-5504")
    @Test(description = "execute console util to export without mentioned file")
    public void runningExportWithoutFile() {
        List<String> commands = getConsoleCommand(fullPath2Util, EXPORT_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.MINOR)
    @Issue("BEO-5504")
    @Test(description = "execute console util to import without mentioned file")
    public void runningImportWithoutFile() {
        List<String> commands = getConsoleCommand(fullPath2Util, IMPORT_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.MINOR)
    @Issue("BEO-5504")
    @Test(description = "execute console util to delete without mentioned file")
    public void runningDeleteWithoutFile() {
        List<String> commands = getConsoleCommand(fullPath2Util, DELETE_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "execute console util to export data from DB into file")
    public void export() {
        // prepare data inserting them in DB using stored procedures
        String suffix = String.valueOf(BaseUtils.getEpoch());
        String roleName = "MaxTest" + suffix;
        String permissionItemName = "Asset_List_Max" + suffix;
        PermissionManagementUtils.insertDataInAllTables(generatedDataFilePath, roleName, "partner*",
                permissionItemName, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, false);

        // export from DB
        List<String> commands = getConsoleCommand(fullPath2Util, EXPORT_KEY + dataFilePath);
        executeCommandsInColsole(commands);

        // checks that created file contains inserted data
        String fileContent = getFileContent(dataFilePath);
        assertThat(fileContent).contains(roleName);
        assertThat(fileContent).contains(permissionItemName);
        assertThat(fileContent).contains("permissionItemObject" + suffix);
        assertThat(fileContent).contains("parameter" + suffix);

        // cleaning
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);
        PermissionsManagementDBUtils.deleteRoleAndItsPermissions(idRoleHavingName);
        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems(permissionItemName);
        PermissionsManagementDBUtils.deletePermissionItem(idPermissionItemHavingName);
        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions(roleName, 0);
        PermissionsManagementDBUtils.deletePermission(idPermissionHavingName);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "execute console util to import data into DB from file having only 4 tables instead of 5")
    public void runningImportFromFileNotHavingAllTables() {
        // remove log file
        deleteFile(path2Log);

        // try to import into DB
        List<String> commands = getConsoleCommand(fullPath2Util, IMPORT_KEY + importOnly4TablesFilePath);
        executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("Import failed: reading from XML resulted in empty data set or data set with less than 5 tables");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "execute console util to try import data into DB from empty file")
    public void runningImportFromEmptyFile() {
        // remove log file
        deleteFile(path2Log);

        // try to import into DB
        List<String> commands = getConsoleCommand(fullPath2Util, IMPORT_KEY + path2EmptyFile);
        executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("Failed importing permissions, ex = System.Xml.XmlException: Root element is missing");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(description = "execute console util to try delete data from DB using empty file")
    public void runningDeleteUsingEmptyFile() {
        // remove log file
        deleteFile(path2Log);

        // try to import into DB
        List<String> commands = getConsoleCommand(fullPath2Util, DELETE_KEY + path2EmptyFile);
        executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("Failed deleting permissions, ex = System.Xml.XmlException: Root element is missing");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "execute console util to import data into DB from valid file")
    public void importFromFile() {
        String suffix = String.valueOf(BaseUtils.getEpoch());
        String roleName = "MaxTest" + suffix;
        String permissionItemName = "Asset_List_Max" + suffix;
        PermissionManagementUtils.generateFileWithInsertedIntoDBData(generatedDataFilePath, roleName, "partner*",
                permissionItemName, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, 1, 2, 3, 4, 5, false);

        // import into DB
        List<String> commands = getConsoleCommand(fullPath2Util, IMPORT_KEY + generatedDataFilePath);
        executeCommandsInColsole(commands);

        // check data in DB
        int rowsInRolesHavingName = getCountRowsHavingRoleNameInRoles(roleName, 0);
        assertThat(rowsInRolesHavingName).isEqualTo(1);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);

        int rowsInPermissionsHavingName = getCountRowsHavingRoleNameInPermissions(roleName, 0);
        assertThat(rowsInPermissionsHavingName).isEqualTo(1);
        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions(roleName, 0);

        int idRolePermission = getCountSpecificRowsFromRolesPermissions(idRoleHavingName, idPermissionHavingName, 0);
        assertThat(idRolePermission).isEqualTo(1);

        int rowsInPermissionItemsHavingName = getCountRowsHavingNameInPermissionItems(permissionItemName);
        assertThat(rowsInPermissionItemsHavingName).isEqualTo(1);
        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems(permissionItemName);

        int rowsInPermissionsPermissions = getCountSpecificRowsFromPermissionsPermissionsItems(idPermissionHavingName,
                idPermissionItemHavingName, 0);
        assertThat(rowsInPermissionsPermissions).isEqualTo(1);

        // cleaning
        PermissionsManagementDBUtils.deleteRoleAndItsPermissions(idRoleHavingName);
        PermissionsManagementDBUtils.deletePermissionItem(idPermissionItemHavingName);
        PermissionsManagementDBUtils.deletePermission(idPermissionHavingName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "execute console util to check items from DB not mentioned in import file should be mentioned in log")
    public void runningImportToCheckLogHasItemsFromDBNotMentionedInFile() {
        // TODO: update test
        // remove log file
        deleteFile(path2Log);

        // insert data in DB
        String suffix = String.valueOf(BaseUtils.getEpoch()) + "inserted";
        String roleName = "MaxTest" + suffix;
        String permissionItemName = "Asset_List_Max" + suffix;
        PermissionManagementUtils.insertDataInAllTables(generatedDataFilePath, roleName, "partner*",
                permissionItemName, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, false);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);

        // generate import file data
        suffix = String.valueOf(BaseUtils.getEpoch());
        roleName = "MaxTest" + suffix;
        permissionItemName = "Asset_List_Max" + suffix;
        PermissionManagementUtils.generateFileWithInsertedIntoDBData(generatedDataFilePath, roleName, "partner*",
                permissionItemName, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, 1, 2, 3, 4, 5, false);

        // try to import into DB
        List<String> commands = getConsoleCommand(fullPath2Util, DELETE_KEY + path2EmptyFile);
        String outputInConsole = executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("ex = System.Xml.XmlException: Root element is missing");
        assertThat(outputInConsole).contains("ex = System.Xml.XmlException: Root element is missing");

        // cleaning
//        PermissionsManagementDBUtils.deleteRoleAndItsPermissions(idRoleHavingName);
//        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems(permissionItemName);
//        PermissionsManagementDBUtils.deletePermissionItem(idPermissionItemHavingName);
//        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions(roleName, 0);
//        PermissionsManagementDBUtils.deletePermission(idPermissionHavingName);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "execute console util to delete data from DB")
    public void deleteFromDB() {
        String suffix = String.valueOf(BaseUtils.getEpoch());
        String roleName = "MaxTest" + suffix;
        String permissionItemName = "Asset_List_Max" + suffix;
        PermissionManagementUtils.generateFileWithInsertedIntoDBData(generatedDataFilePath, roleName, "partner*",
                permissionItemName, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, 1, 2, 3, 4, 5, false);

        // import into DB
        List<String> commands = getConsoleCommand(fullPath2Util, IMPORT_KEY + generatedDataFilePath);
        executeCommandsInColsole(commands);

        // check data in DB
        int rowsInRolesHavingName = getCountRowsHavingRoleNameInRoles(roleName, 0);
        assertThat(rowsInRolesHavingName).isEqualTo(1);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);

        int rowsInPermissionsHavingName = getCountRowsHavingRoleNameInPermissions(roleName, 0);
        assertThat(rowsInPermissionsHavingName).isEqualTo(1);
        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions(roleName, 0);

        int idRolePermission = getCountSpecificRowsFromRolesPermissions(idRoleHavingName, idPermissionHavingName, 0);
        assertThat(idRolePermission).isEqualTo(1);

        int rowsInPermissionItemsHavingName = getCountRowsHavingNameInPermissionItems(permissionItemName);
        assertThat(rowsInPermissionItemsHavingName).isEqualTo(1);
        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems(permissionItemName);

        int rowsInPermissionsPermissions = getCountSpecificRowsFromPermissionsPermissionsItems(idPermissionHavingName,
                idPermissionItemHavingName, 0);
        assertThat(rowsInPermissionsPermissions).isEqualTo(1);

        // remove log file
        deleteFile(path2Log);

        // delete from DB
        commands = getConsoleCommand(fullPath2Util, DELETE_KEY + generatedDataFilePath);
        executeCommandsInColsole(commands);

        // DB should be empty
        rowsInRolesHavingName = getCountRowsHavingRoleNameInRoles(roleName, 0);
        assertThat(rowsInRolesHavingName).isEqualTo(0);

        rowsInPermissionsHavingName = getCountRowsHavingRoleNameInPermissions(roleName, 0);
        assertThat(rowsInPermissionsHavingName).isEqualTo(0);

        rowsInPermissionItemsHavingName = getCountRowsHavingNameInPermissionItems(permissionItemName + suffix);
        assertThat(rowsInPermissionItemsHavingName).isEqualTo(0);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "execute console util to import already existed data into DB from valid file")
    public void importAlreadyExistedFromFile() {
        String suffix = String.valueOf(BaseUtils.getEpoch());
        String roleName = "MaxTest" + suffix;
        String permissionItemName = "Asset_List_Max" + suffix;
        PermissionManagementUtils.generateFileWithInsertedIntoDBData(generatedDataFilePath, roleName, "partner*",
                permissionItemName, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, 1, 2, 3, 4, 5, false);

        // import into DB
        List<String> commands = getConsoleCommand(fullPath2Util, IMPORT_KEY + generatedDataFilePath);
        executeCommandsInColsole(commands);

        // retry import
        executeCommandsInColsole(commands);

        // check data in DB
        int rowsInRolesHavingName = getCountRowsHavingRoleNameInRoles(roleName, 0);
        assertThat(rowsInRolesHavingName).isEqualTo(1);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);

        int rowsInPermissionsHavingName = getCountRowsHavingRoleNameInPermissions(roleName, 0);
        assertThat(rowsInPermissionsHavingName).isEqualTo(1);
        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions(roleName, 0);

        int idRolePermission = getCountSpecificRowsFromRolesPermissions(idRoleHavingName, idPermissionHavingName, 0);
        assertThat(idRolePermission).isEqualTo(1);

        int rowsInPermissionItemsHavingName = getCountRowsHavingNameInPermissionItems(permissionItemName);
        assertThat(rowsInPermissionItemsHavingName).isEqualTo(1);
        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems(permissionItemName);

        int rowsInPermissionsPermissions = getCountSpecificRowsFromPermissionsPermissionsItems(idPermissionHavingName,
                idPermissionItemHavingName, 0);
        assertThat(rowsInPermissionsPermissions).isEqualTo(1);

        // cleaning
        PermissionsManagementDBUtils.deleteRoleAndItsPermissions(idRoleHavingName);
        PermissionsManagementDBUtils.deletePermissionItem(idPermissionItemHavingName);
        PermissionsManagementDBUtils.deletePermission(idPermissionHavingName);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "execute console util to try delete data in DB using file with invalid tags")
    public void runningDeleteUsingFileWithInvalidTags() {
        // insert role in DB
        String suffix = String.valueOf(BaseUtils.getEpoch());
        String roleName = "MaxTest" + suffix;
        PermissionsManagementDBUtils.insertRole(roleName);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles("MaxTest" + suffix, 0);

        PermissionManagementUtils.generateFileWithInvalidTagForRole(generatedDataFilePath, roleName, idRoleHavingName);

        // try delete
        List<String> commands = getConsoleCommand(fullPath2Util, DELETE_KEY + generatedDataFilePath);
        executeCommandsInColsole(commands);

        // check data still in DB
        int rowsInRolesHavingName = getCountRowsHavingRoleNameInRoles(roleName, 0);
        assertThat(rowsInRolesHavingName).isEqualTo(1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description = "execute console util to delete data in 1 related table of DB")
    public void deleteOnlyFromOneTable() {
        // insert role in DB
        String suffix = String.valueOf(BaseUtils.getEpoch());
        String roleName = "MaxTest" + suffix;
        PermissionsManagementDBUtils.insertRole(roleName);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);

        PermissionManagementUtils.generateFileForRole(generatedDataFilePath, roleName, idRoleHavingName);

        // delete
        List<String> commands = getConsoleCommand(fullPath2Util, DELETE_KEY + generatedDataFilePath);
        executeCommandsInColsole(commands);

        // check data deleted from DB
        int rowsInRolesHavingName = getCountRowsHavingRoleNameInRoles(roleName, 0);
        assertThat(rowsInRolesHavingName).isEqualTo(0);
    }

    @Severity(SeverityLevel.MINOR)
    @Issue("BEO-5504")
    @Test(description = "execute console util to export in JSON without mentioned file")
    public void runningExportJsonWithoutFile() {
        List<String> commands = getConsoleCommand(fullPath2Util, EXPORT_JSON_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.MINOR)
    @Issue("BEO-5504")
    @Test(description = "execute console util to import in JSON without mentioned file")
    public void runningImportJsonWithoutFile() {
        List<String> commands = getConsoleCommand(fullPath2Util, IMPORT_JSON_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "execute console util to export in JSON from DB")
    public void exportJson() throws IOException {
        // clean folder with logs
        FileUtils.cleanDirectory(new File(path2JsonFolder));

        // prepare data inserting them in DB using stored procedures
        String suffix = String.valueOf(BaseUtils.getEpoch());
        String roleName = "MaxTest" + suffix;
        String permissionItemName = "Asset_List_Max" + suffix;
        String serviceName = "asset";
        PermissionManagementUtils.insertDataInAllTables(generatedDataFilePath, roleName, "partner*",
                permissionItemName, serviceName, "list", "permissionItemObject" + suffix,
                "parameter" + suffix, true);
        // command
        List<String> commands = getConsoleCommand(fullPath2Util, EXPORT_JSON_KEY + path2JsonFolder);
        executeCommandsInColsole(commands);

        String importFileContent = getFileContent(generatedDataFilePath);
        assertThat(importFileContent).contains(roleName);
        assertThat(importFileContent).contains(permissionItemName);
        checkActionResult(serviceName, importFileContent);

        // cleaning
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);
        PermissionsManagementDBUtils.deleteRoleAndItsPermissions(idRoleHavingName);
        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems(permissionItemName);
        PermissionsManagementDBUtils.deletePermissionItem(idPermissionItemHavingName);
        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions(roleName, 0);
        PermissionsManagementDBUtils.deletePermission(idPermissionHavingName);
    }

//    @Severity(SeverityLevel.CRITICAL)
//    @Test(description = "execute console util to import from JSON into DB")
//    public void importJson() throws IOException {
//        // clean folder with logs
//        FileUtils.cleanDirectory(new File(path2JsonFolder));
//
//        // export command to get results
//        List<String> commands = getConsoleCommand(fullPath2Util, EXPORT_JSON_KEY + path2JsonFolder);
//        executeCommandsInColsole(commands);
//
//        // prepare data
//        String suffix = String.valueOf(BaseUtils.getEpoch());
//        String roleName = "MaxTest" + suffix;
//        String permissionItemName = "Asset_List_Max" + suffix;
//        String serviceName = "asset";
//        fillFilesWithImportData(roleName, permissionItemName, serviceName);
//
//        PermissionManagementUtils.insertDataInAllTables(generatedDataFilePath, roleName, "partner*",
//                permissionItemName, serviceName, "list", "permissionItemObject" + suffix,
//                "parameter" + suffix, true);
//
//        String importFileContent = getFileContent(generatedDataFilePath);
//        assertThat(importFileContent).contains(roleName);
//        assertThat(importFileContent).contains(permissionItemName);
//        checkActionResult(serviceName, importFileContent);
//
//        // cleaning
//        int idRoleHavingName = getIdRecordHavingRoleNameInRoles(roleName, 0);
//        PermissionsManagementDBUtils.deleteRoleAndItsPermissions(idRoleHavingName);
//        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems(permissionItemName);
//        PermissionsManagementDBUtils.deletePermissionItem(idPermissionItemHavingName);
//        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions(roleName, 0);
//        PermissionsManagementDBUtils.deletePermission(idPermissionHavingName);
//    }

    // method checks that all related files contain information related to service
    /**
     *
     * @param serviceName - name of service (e.g.: "asset")
     * @param importFileContent - file created during adding data into DB by util PermissionManagementUtils.insertDataInAllTables
     * data in that file related to data from files with roles info, permissions info and methods info and separated by symbol ";"
     */
    void checkActionResult(String serviceName, String importFileContent) {
        // data prepared so that they splitted by symbol ";"
        String[] results = importFileContent.split(";");
        String contentOfJsonFile = getFileContent(path2JsonRoles)
                .replaceAll(" ", "");
                //.replaceAll("\\t", "");
        assertThat(contentOfJsonFile).contains(results[0]
                .replaceAll(" ", ""));
        contentOfJsonFile = getFileContent(path2JsonPermissions)
                .replaceAll(" ", "");
                //.replaceAll("\\t", "");
        assertThat(contentOfJsonFile).contains(results[1]
                .replaceAll(" ", ""));
        contentOfJsonFile = getFileContent(path2JsonMethods + serviceName + ".json")
                .replaceAll(" ", "");
                //.replaceAll("\\t", "");
        assertThat(contentOfJsonFile).contains(results[2].replaceAll(" ", ""));
    }
}
