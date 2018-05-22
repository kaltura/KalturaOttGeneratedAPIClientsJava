package com.kaltura.client.test.tests.featuresTests.four_eight;

import com.kaltura.client.test.utils.PermissionManagementUtils;
import com.kaltura.client.test.utils.dbUtils.DBUtils;
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
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-4885
 */
public class PermissionsManagement {
    // TODO: discuss where to save these values
    String path2Log = "C:\\log\\permissions\\permissions.log";
    String path2Util = "C:\\123\\222\\";
    String path2EmptyFile = path2Util + "333\\" + "empty_file.txt";
    String mainFile = "PermissionsDeployment.exe";
    String dataFilePath = path2Util + "333\\" + "exp1.txt";
    String importOnly4TablesFilePath = path2Util + "333\\" + "importOnly4Tables.txt";

    public static final String EXPORT_KEY = "e=";
    public static final String IMPORT_KEY = "i=";
    public static final String DELETE_KEY = "d=";

    @Test(enabled = false, description = "just for deletion")
    public void deleteData() {
        //long roleId = 477L;
        long permissionId = 32L;
        /*long permissionItemId = 544L;
        long permissionPermissionItemId = 1068;*/

        //DBUtils.deleteRoleAndItsPermissions((int)roleId);
        DBUtils.deletePermission((int)permissionId);
        /*DBUtils.deletePermissionItem((int)permissionItemId);
        DBUtils.deletePermissionPermissionItem((int)permissionPermissionItemId);*/
    }

    @Test(enabled = false, description = "execute console util without parameters")
    private void checkRunningWithoutParameters() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("Permissions deployment tool");
        assertThat(consoleOutput).contains("Shortcut: e");
        assertThat(consoleOutput).contains("Shortcut: i");
    }

    @Test(enabled = false, description = "execute console util to export without mentioned file")
    private void checkRunningExportWithoutFile() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(EXPORT_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Test(enabled = false, description = "execute console util to import without mentioned file")
    private void checkRunningImportWithoutFile() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(IMPORT_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Test(enabled = false, description = "execute console util to delete without mentioned file")
    private void checkRunningDeleteWithoutFile() {
        List<String> commands = new ArrayList<>();
        commands.add(path2Util + mainFile);
        commands.add(DELETE_KEY);
        String consoleOutput = executeCommandsInColsole(commands);

        assertThat(consoleOutput).contains("The system cannot find the file specified");
    }

    @Test(enabled = false, description = "execute console util to export data from DB into file")
    private void checkRunningExport() {
        // prepare data inserting them in DB using stored procedures
        String suffix = String.valueOf(getTimeInEpoch(0));
        PermissionManagementUtils.insertDataInAllTables(path2Util, "MaxTest" + suffix, "partner*",
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

    @Test(enabled = false, description = "execute console util to import data into DB from file having only 4 tables instead of 5")
    private void checkRunningImportFromFileNotHavingAllTables() {
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

    @Test(enabled = false, description = "execute console util to try import data into DB from empty file")
    private void checkRunningImportFromEmptyFile() {
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

    @Test(/*enabled = false,*/ description = "execute console util to try delete data from DB using empty file")
    private void checkRunningDeleteUsingEmptyFile() {
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

    // TODO: check how to use it
    @Test(enabled = false)
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
