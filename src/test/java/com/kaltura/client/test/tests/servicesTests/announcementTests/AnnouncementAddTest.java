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
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.kaltura.client.services.AnnouncementService.*;
import static com.kaltura.client.services.OttUserService.login;
import static com.kaltura.client.test.utils.BaseUtils.getEpochInUtcTime;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getAnnouncementResultMessageId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class AnnouncementAddTest extends BaseTest {

    @BeforeClass
    private void announcement_add_tests_before_class() {
        executor.executeSync(login(partnerId, "alon2986", "alon2986"));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("announcement/action/add")
    @Test(enabled = true)
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

        // get list of announcements
        AnnouncementFilter filter = new AnnouncementFilter();
        filter.setOrderBy("NONE");

        FilterPager pager = new FilterPager();
        pager.setPageSize(10);
        pager.setPageIndex(1);

        ListAnnouncementBuilder listAnnouncementBuilder = list(filter, pager).setKs(getOperatorKs());

        // wait until announcement will be send
        await()
                .atMost(5, TimeUnit.MINUTES)
                .pollInterval(30, TimeUnit.SECONDS)
                .until(isAnnouncementSent(listAnnouncementBuilder, announcement.getId()));

        // assert confirmation from Amazon
        List<String> ids = getAnnouncementResultMessageId(announcement.getId());
        assertThat(ids).isNotNull();
        assertThat(ids.size()).isGreaterThan(0);

        // assert email sent
//        assertThat(isEmailReceived(announcement.getMessage(), true)).isTrue();
    }

    // helper methods
    private static Callable<Boolean> isAnnouncementSent(ListAnnouncementBuilder listAnnouncementBuilder, int announcementId) {
        return () -> {
            // get announcement list
            List<Announcement> announcements = executor.executeSync(listAnnouncementBuilder)
                    .results
                    .getObjects();

            // get created announcement from list
            Announcement announcement = announcements.stream().
                    filter(a -> a.getId().equals(announcementId)).
                    findFirst()
                    .orElse(null);

            // check if announcement sent
            return announcement != null &&
                    announcement.getStatus().equals(AnnouncementStatus.SENT);
        };
    }
}
