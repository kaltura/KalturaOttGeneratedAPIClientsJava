package com.kaltura.client.test.tests.servicesTests.SlowTestsRunner;

import com.kaltura.client.test.tests.BaseTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SlowTestsRunner extends BaseTest{

    @BeforeSuite(groups = "slow_before")
    public void setupSlowTests(){
        baseTest_beforeSuite();
    }

    @Test(alwaysRun = true, groups = "slow", dependsOnGroups = {"slow_before", "slow_after"})
    public void prepareSlowTests(){


    }
}
