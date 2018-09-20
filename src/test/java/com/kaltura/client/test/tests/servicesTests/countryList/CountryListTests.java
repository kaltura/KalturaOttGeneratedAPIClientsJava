package com.kaltura.client.test.tests.servicesTests.countryList;

import com.kaltura.client.enums.CountryOrderBy;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.Country;
import com.kaltura.client.types.CountryFilter;
import com.kaltura.client.types.ListResponse;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.util.*;

import static com.kaltura.client.services.CountryService.*;
import static org.assertj.core.api.Assertions.assertThat;

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

        // country/action/list
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);
        assertThat(countryResponse.error).isNull();
        assertThat(countryResponse.results.getTotalCount()).isGreaterThan(200);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - verify results order (by name)")
    @Test
    private void listCountryOrder() {

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setOrderBy(CountryOrderBy.NAME_ASC.toString());
        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());

        // country/action/list
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);
        List<String> newList = new ArrayList<>();
        for (Country country: countryResponse.results.getObjects()) {
            newList.add(country.getName());
        }
        ArrayList<String> temp = new ArrayList<>(newList);
        Collections.sort(temp);
        assertThat(temp).isEqualTo(newList);
    }
}
