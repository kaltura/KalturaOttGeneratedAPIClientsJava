package com.kaltura.client.test.tests.servicesTests.announcementTests;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.Announcement;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AnnouncementAddTest extends BaseTest {

    @BeforeClass
    private void announcement_add_tests_before_class() {

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("announcement/action/add")
    @Test
    private void addAnnouncement() {
        Announcement announcement = new Announcement();
//        announcement.set
//        AnnouncementService.add().setKs(getOperatorKs());
    }
}
