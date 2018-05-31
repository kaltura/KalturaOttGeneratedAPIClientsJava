package com.kaltura.client.test.utils;

import com.google.common.reflect.ClassPath;
import com.kaltura.client.Logger;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.Set;

public class StatisticsUtils extends BaseUtils {

    public static void displayStatisticsDisabledAndCriticalTests(String packageName) {
        Logger.getLogger(StatisticsUtils.class).debug("Package: [" + packageName + "]");
        Set<ClassPath.ClassInfo> allClasses = null;
        try {
            allClasses = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClassesRecursive(packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int totalTests = 0;
        int disabledTests = 0;
        int criticalSeverityTests = 0;
        for (ClassPath.ClassInfo classInfo : allClasses) {
            //Logger.getLogger(StatisticsUtils.class).debug("Class: [" + classInfo.getSimpleName() + "]");
            Class clazz = classInfo.load();
            Method[] methods = clazz.getDeclaredMethods();
            //Logger.getLogger(StatisticsUtils.class).debug("Methods:");
            for (int i = 0; i < methods.length; i++) {
                //Logger.getLogger(StatisticsUtils.class).debug(methods[i].getName());
                //Logger.getLogger(StatisticsUtils.class).debug("Annotations:");
                for (Annotation annotation: methods[i].getDeclaredAnnotations()) {
                    if (annotation.toString().contains("org.testng.annotations.Test")) {
                        totalTests++;
                        if (annotation.toString().contains("enabled=false")) {
                            // Logger.getLogger(StatisticsUtils.class).debug(methods[i].getName());
                            // Logger.getLogger(StatisticsUtils.class).debug(annotation.toString());
                            disabledTests++;
                        }
                    }
                    if (annotation.toString().contains("io.qameta.allure.Severity(value=CRITICAL)")) {
                        criticalSeverityTests++;
                    }
                }
            }
            //Logger.getLogger(StatisticsUtils.class).debug("________________________________________________");
        }
        Logger.getLogger(StatisticsUtils.class).debug("total amount of tests: " + totalTests);
        DecimalFormat df = new DecimalFormat("#.00");
        Logger.getLogger(StatisticsUtils.class).debug("disabled tests: " + disabledTests + " or "
                + df.format(disabledTests*1.0/totalTests*100) + "%");
        Logger.getLogger(StatisticsUtils.class).debug("tests with critical severity: " + criticalSeverityTests + " or "
                + df.format(criticalSeverityTests*1.0/totalTests*100) + "%");
    }
}

