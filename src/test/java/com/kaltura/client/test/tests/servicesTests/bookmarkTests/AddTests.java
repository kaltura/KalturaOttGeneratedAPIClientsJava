package com.kaltura.client.test.tests.servicesTests.bookmarkTests;

import com.kaltura.client.test.servicesImpl.BookmarkServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.Bookmark;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AddTests extends BaseTest {

    @Description("bookmark/action/add - add")
    @Test
    private void add() {
        Bookmark bookmark = new Bookmark();

        Response<Boolean> booleanResponse = BookmarkServiceImpl.add(administratorKs, bookmark);
        assertThat(booleanResponse.results.booleanValue()).isTrue();
        assertThat(booleanResponse.error).isNull();

    }
}
