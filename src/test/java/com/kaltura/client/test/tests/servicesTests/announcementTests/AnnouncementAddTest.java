package com.kaltura.client.test.tests.servicesTests.announcementTests;

import com.kaltura.client.enums.AnnouncementRecipientsType;
import com.kaltura.client.enums.AnnouncementStatus;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.Announcement;
import com.kaltura.client.types.AnnouncementFilter;
import com.kaltura.client.types.FilterPager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.AnnouncementService.add;
import static com.kaltura.client.services.AnnouncementService.list;
import static com.kaltura.client.test.utils.BaseUtils.getEpochInUtcTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class AnnouncementAddTest extends BaseTest {

    @BeforeClass
    private void announcement_add_tests_before_class() {

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("announcement/action/add")
    @Test(enabled = false)
    private void addAnnouncement() {
        // set announcement
        long epoch = getEpochInUtcTime(1);
        Announcement announcement = new Announcement();
        announcement.setName("Announcement_" + epoch);
        announcement.setMailSubject(announcement.getName());
        announcement.setMessage(announcement.getName());
        announcement.setEnabled(true);
        announcement.setStartTime(epoch);
        announcement.setTimezone("UTC");
        announcement.setRecipients(AnnouncementRecipientsType.ALL);

        // add announcement
        announcement = executor.executeSync(add(announcement)
                .setKs(getOperatorKs()))
                .results;

        // wait until announcement will be send
        await()
                .atMost(3, TimeUnit.MINUTES)
                .pollInterval(30, TimeUnit.SECONDS)
                .until(() -> getEpochInUtcTime(0) > epoch + 30);

        // get list of announcements
        AnnouncementFilter filter = new AnnouncementFilter();
        filter.setOrderBy("NONE");

        FilterPager pager = new FilterPager();
        pager.setPageSize(10);
        pager.setPageIndex(1);

        List<Announcement> announcements = executor.executeSync(list(filter, pager)
                .setKs(getOperatorKs()))
                .results
                .getObjects();

        // get created announcement from list
        Announcement finalAnnouncement = announcement;
        announcement = announcements.stream().
                filter(a -> a.getId().equals(finalAnnouncement.getId())).
                findFirst()
                .orElse(null);

        // assertions
        assertThat(announcement).isNotNull();
        assertThat(announcement.getStatus()).isEqualTo(AnnouncementStatus.SENT);
    }
}
