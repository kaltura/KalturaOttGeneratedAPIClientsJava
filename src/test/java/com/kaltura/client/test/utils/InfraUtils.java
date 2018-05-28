package com.kaltura.client.test.utils;

import com.google.common.reflect.ClassPath;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.Set;

public class InfraUtils {

    public static void writeUtilsListToFile() throws IOException {
        String packageName = "com.kaltura.client.test.utils";

        File file = new File("src/test/resources/utils_list.txt");
        FileUtils.writeStringToFile(file, "package - " + packageName, Charset.defaultCharset());
        FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);
        FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);

        Set<ClassPath.ClassInfo> allClasses = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(packageName);
        for (ClassPath.ClassInfo classInfo: allClasses) {
            FileUtils.writeStringToFile(file, "class - " + classInfo.getSimpleName() + ":", Charset.defaultCharset(), true);
            FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);

            Class clazz = classInfo.load();
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (Modifier.isStatic(methods[i].getModifiers())) {
                    FileUtils.writeStringToFile(file, methods[i].getName(), Charset.defaultCharset(), true);
                    FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);
                }
            }
            FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);
        }
    }

    public static void writeDbUtilsListToFile() throws IOException {
        String packageName = "com.kaltura.client.test.utils.dbUtils";

        File file = new File("src/test/resources/db_utils_list.txt");
        FileUtils.writeStringToFile(file, "package - " + packageName, Charset.defaultCharset());
        FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);
        FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);

        Set<ClassPath.ClassInfo> allClasses = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClasses(packageName);
        for (ClassPath.ClassInfo classInfo: allClasses) {
            FileUtils.writeStringToFile(file, "class - " + classInfo.getSimpleName() + ":", Charset.defaultCharset(), true);
            FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);

            Class clazz = classInfo.load();
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                if (Modifier.isStatic(methods[i].getModifiers())) {
                    FileUtils.writeStringToFile(file, methods[i].getName(), Charset.defaultCharset(), true);
                    FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);
                }
            }
            FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR, Charset.defaultCharset(), true);
        }
    }
}
