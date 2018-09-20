package com.kaltura.client.test.tests.servicesTests.countryList;

import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.Country;
import com.kaltura.client.types.CountryFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import static com.kaltura.client.services.CountryService.*;

public class CountryListTests extends BaseTest {

    private void list_tests_before_class() {

    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - empty filter - get all countries")
    @Test
    private void listCountry() {
        CountryFilter countryFilter = new CountryFilter();
        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);

    }


}
