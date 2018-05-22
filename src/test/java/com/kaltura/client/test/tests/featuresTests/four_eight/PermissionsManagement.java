package com.kaltura.client.test.tests.featuresTests.four_eight;

import com.kaltura.client.test.utils.dbUtils.DBUtils;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import static com.kaltura.client.test.utils.PermissionManagementUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * Class to test functionality described in https://kaltura.atlassian.net/browse/BEO-4885
 */
public class PermissionsManagement {
    // TODO: discuss where to save these values
    String path2Util = "C:\\123\\222\\";
    String mainFile = "PermissionsDeployment.exe";
    String dataFilePath = path2Util + "333\\" + "exp1.txt";

    @Test(description = "just for deletion")
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

    // TODO: change descriptions later
    @Test(enabled = false, description = "execute stored procedures related insert data into permission management related tables")
    public void insertDataIntoTables() {
        String suffix = "00002";
        String role = "MaxTest" + suffix;
        long roleId = DBUtils.insertRole(role);
        System.out.println("Role: " + roleId); // 468
        //DBUtils.deleteRoleAndItsPermissions((int)roleId);
        long permissionId = DBUtils.insertPermission(role, 2, "partner*");
        System.out.println("Permission: " + permissionId); // 26
        long permissionRoleId = DBUtils.insertPermissionRole(roleId, permissionId, 0);
        System.out.println("PermissionRole: " + permissionRoleId); // 622
        String name = "Asset_List_Max" + suffix;
        String service = "asset";
        String action = "list";
        String permissionItemObject = "permissionItemObject";
        String parameter = "parameter";
        long permissionItemId = DBUtils.insertPermissionItem(name, 1, service, action, permissionItemObject, parameter);
        System.out.println("PermissionItem: " + permissionItemId); // 539
        long permissionPermissionItemId = DBUtils.insertPermissionPermissionItem(permissionId, permissionItemId, 0);
        System.out.println("PermissionPermissionItem: " + permissionPermissionItemId);//1063

        // generation of XML file with insertedData
        try {
            File file = new File(path2Util + "333\\" + "import_data.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println("<?xml version=\"1.0\" standalone=\"yes\"?>");
            writer.println("<permissions_dataset>");
            printRole(writer, roleId, role);
            printRolePermission(writer, permissionRoleId, roleId, permissionId, 0, role, role);
            printPermission(writer, permissionId, role, 2, "partner*");
            printPermissionItem(writer, permissionItemId, name, 1, service, action, permissionItemObject, parameter);
            printPermissionPermissionItem(writer, permissionPermissionItemId, permissionId, permissionItemId, 0, name, role);
            writer.println("</permissions_dataset>");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false, description = "execute console util without parameters")
    private void checkRunningWithoutParameters() {
        StringBuilder output = new StringBuilder();

        ProcessBuilder pb = new ProcessBuilder(path2Util + mainFile);
        pb.redirectErrorStream(true);
        BufferedReader inStreamReader;
        try {
            Process process = pb.start();
            inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = inStreamReader.readLine()) != null){
                output.append(line);
            }

            assertThat(output.toString()).contains("Permissions deployment tool");
            assertThat(output.toString()).contains("Shortcut: e");
            assertThat(output.toString()).contains("Shortcut: i");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // return input stream back
            inStreamReader = new BufferedReader(new InputStreamReader(System.in));
        }
    }

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
