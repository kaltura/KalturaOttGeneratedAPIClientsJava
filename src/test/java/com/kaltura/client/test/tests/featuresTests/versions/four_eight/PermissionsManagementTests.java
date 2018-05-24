package com.kaltura.client.test.tests.featuresTests.versions.four_eight;

import com.kaltura.client.test.utils.PermissionManagementUtils;
import com.kaltura.client.test.utils.dbUtils.PermissionsManagementDBUtils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import static com.kaltura.client.test.utils.PermissionManagementUtils.*;
import static com.kaltura.client.test.utils.dbUtils.PermissionsManagementDBUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static com.kaltura.client.test.utils.BaseUtils.getTimeInEpoch;
import static com.kaltura.client.test.utils.BaseUtils.getFileContent;
import static com.kaltura.client.test.utils.BaseUtils.deleteFile;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-4885
 */
public class PermissionsManagementTests {

    String mainFile = "PermissionsDeployment.exe";
    String path2Log = "C:\\log\\permissions\\permissions.log";
    String path2Util = "C:\\123\\222\\";
    String path2EmptyFile = path2Util + "333\\" + "empty_file.txt";
    String dataFilePath = path2Util + "333\\" + "exp1.txt";
    String generatedDataFilePath = path2Util + "333\\" + "import.txt";
    String importOnly4TablesFilePath = path2Util + "333\\" + "importOnly4Tables.txt";

    public static final String EXPORT_KEY = "e=";
    public static final String IMPORT_KEY = "i=";
    public static final String DELETE_KEY = "d=";

    @Test(groups = {"Permission management"}, description = "just for deletion")
    public void deleteData() {
        //long roleId = 477L;
        long permissionId = 32L;
        /*long permissionItemId = 544L;
        long permissionPermissionItemId = 1068;*/

        //PermissionsManagementDBUtils.deleteRoleAndItsPermissions((int)roleId);
        PermissionsManagementDBUtils.deletePermission((int)permissionId);
        /*PermissionsManagementDBUtils.deletePermissionItem((int)permissionItemId);
        PermissionsManagementDBUtils.deletePermissionPermissionItem((int)permissionPermissionItemId);*/
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"Permission management"}, description = "execute console util without parameters")
    public void runningWithoutParameters() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("Permissions deployment tool");
        assertThat(consoleOutput).contains("Shortcut: e");
        assertThat(consoleOutput).contains("Shortcut: i");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"Permission management"}, description = "execute console util to export without mentioned file")
    public void runningExportWithoutFile() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(EXPORT_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"Permission management"}, description = "execute console util to import without mentioned file")
    public void runningImportWithoutFile() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(IMPORT_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"Permission management"}, description = "execute console util to delete without mentioned file")
    public void runningDeleteWithoutFile() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(DELETE_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"Permission management"}, description = "execute console util to export data from DB into file")
    public void export() {
        // prepare data inserting them in DB using stored procedures
        String suffix = String.valueOf(getTimeInEpoch(0));
        PermissionManagementUtils.insertDataInAllTables(generatedDataFilePath, "MaxTest" + suffix, "partner*",
                "Asset_List_Max" + suffix, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix);

        // export from DB
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(EXPORT_KEY + dataFilePath);
        executeCommandsInColsole(commands);

        // checks that created file contains inserted data
        String fileContent = getFileContent(dataFilePath);
        assertThat(fileContent).contains("MaxTest" + suffix);
        assertThat(fileContent).contains("Asset_List_Max" + suffix);
        assertThat(fileContent).contains("permissionItemObject" + suffix);
        assertThat(fileContent).contains("parameter" + suffix);
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"Permission management"}, description = "execute console util to import data into DB from file having only 4 tables instead of 5")
    public void runningImportFromFileNotHavingAllTables() {
        // remove log file
        deleteFile(path2Log);

        // try to import into DB
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(IMPORT_KEY + importOnly4TablesFilePath);
        executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("Import failed: reading from XML resulted in empty data set or data set with less than 5 tables");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"Permission management"}, description = "execute console util to try import data into DB from empty file")
    public void runningImportFromEmptyFile() {
        // remove log file
        deleteFile(path2Log);

        // try to import into DB
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(IMPORT_KEY + path2EmptyFile);
        executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("Failed importing permissions, ex = System.Xml.XmlException: Root element is missing");
    }

    @Severity(SeverityLevel.MINOR)
    @Test(groups = {"Permission management"}, description = "execute console util to try delete data from DB using empty file")
    public void runningDeleteUsingEmptyFile() {
        // remove log file
        deleteFile(path2Log);

        // try to import into DB
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(DELETE_KEY + path2EmptyFile);
        executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("Failed deleting permissions, ex = System.Xml.XmlException: Root element is missing");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"Permission management"}, description = "execute console util to import data into DB from valid file")
    public void importFromFile() {
        String suffix = String.valueOf(getTimeInEpoch(0));
        PermissionManagementUtils.generateFileWithInsertedIntoDBData(generatedDataFilePath, "MaxTest" + suffix, "partner*",
                "Asset_List_Max" + suffix, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, 1, 2, 3, 4, 5);

        // import into DB
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(IMPORT_KEY + generatedDataFilePath);
        executeCommandsInColsole(commands);

        // check data in DB
        int rowsInRolesHavingName = getCountRowsHavingRoleNameInRoles("MaxTest" + suffix, 0);
        assertThat(rowsInRolesHavingName).isEqualTo(1);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles("MaxTest" + suffix, 0);

        int rowsInPermissionsHavingName = getCountRowsHavingRoleNameInPermissions("MaxTest" + suffix, 0);
        assertThat(rowsInPermissionsHavingName).isEqualTo(1);
        int idPermissionHavingName = getIdRecordHavingRoleNameInPermissions("MaxTest" + suffix, 0);

        int idRolePermission = getCountSpecificRowsFromRolesPermissions(idRoleHavingName, idPermissionHavingName, 0);
        assertThat(idRolePermission).isEqualTo(1);

        int rowsInPermissionItemsHavingName = getCountRowsHavingNameInPermissionItems("Asset_List_Max" + suffix, 0);
        assertThat(rowsInPermissionItemsHavingName).isEqualTo(1);
        int idPermissionItemHavingName = getIdRecordHavingNameInPermissionItems("Asset_List_Max" + suffix, 0);

        int rowsInPermissionsPermissions = getCountSpecificRowsFromPermissionsPermissionsItems(idPermissionHavingName,
                idPermissionItemHavingName, 0);
        assertThat(rowsInPermissionsPermissions).isEqualTo(1);
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(groups = {"Permission management"}, description = "execute console util to check items from DB not mentioned in import file should be mentioned in log")
    public void runningImportToCheckLogHasItemsFromDBNotMentionedInFile() {
        // remove log file
        deleteFile(path2Log);

        // insert data in DB
        String suffix = String.valueOf(getTimeInEpoch(0)) + "inserted";
        PermissionManagementUtils.insertDataInAllTables(generatedDataFilePath, "MaxTest" + suffix, "partner*",
                "Asset_List_Max" + suffix, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix);
        int idRoleHavingName = getIdRecordHavingRoleNameInRoles("MaxTest" + suffix, 0);

        // generate import file data
        suffix = String.valueOf(getTimeInEpoch(0));
        PermissionManagementUtils.generateFileWithInsertedIntoDBData(generatedDataFilePath, "MaxTest" + suffix, "partner*",
                "Asset_List_Max" + suffix, "asset", "list", "permissionItemObject" + suffix,
                "parameter" + suffix, 1, 2, 3, 4, 5);

        // try to import into DB
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(DELETE_KEY + path2EmptyFile);
        executeCommandsInColsole(commands);

        String fileContent = getFileContent(path2Log);
        assertThat(fileContent).contains("!!NOT EXISTS IN SOURCE!! Table : role Id : " + idRoleHavingName + " Name : " + "MaxTest" + suffix);
        assertThat(fileContent).contains("Asset_List_Max" + suffix);
        assertThat(fileContent).contains("permissionItemObject" + suffix);
        assertThat(fileContent).contains("parameter" + suffix);
    }
    
    // TODO: check how to use it
    @Test(enabled = false, groups = {"Permission management"})
    public void readXMLFile() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(dataFilePath);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        assertThat(document).isNotNull();
        String id = document.getElementsByTagName("role").item(1).getChildNodes().item(1).getTextContent();
        String name = document.getElementsByTagName("role").item(1).getChildNodes().item(3).getTextContent();
        System.out.println("role id = " + id);
        System.out.println("role name = " + name);
        System.out.println("# of roles: " + document.getElementsByTagName("role").getLength());
    }
}
