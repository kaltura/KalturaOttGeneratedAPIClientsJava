package com.kaltura.client.test.tests.servicesTests.SlowTestsRunner;

import com.kaltura.client.test.tests.BaseTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class SlowTestsRunner extends BaseTest{

    @BeforeSuite(groups = "slow_before")
    public void setupSlowTests(Method method){
        baseTest_beforeSuite();
        baseTest_beforeMethod(method);
    }

    @Test(groups = "slow", dependsOnGroups = {"slow_before"})
    public void prepareSlowTests(){
        System.out.println("waiting timeout");

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("finished timeout");
    }

    @Test(alwaysRun = true, groups = "slow", dependsOnGroups = {"slow_after"}, dependsOnMethods = {"prepareSlowTests"})
    public void succedeed(Method method){
        System.out.println("Good shot! Proud of you!");
    }

}
