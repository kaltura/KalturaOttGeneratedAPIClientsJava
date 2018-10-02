package com.kaltura.client.test.tests.servicesTests.countryTests;

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
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.ingestUtils.BaseIngestUtils.DEFAULT_LANGUAGE;
import static org.assertj.core.api.Assertions.assertThat;

public class CountryListTests extends BaseTest {

    private String countryId = "1";
    private String countryId2 = "2";

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

        // country/action/list - order by name ASC
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);
        List<String> newList = new ArrayList<>();
        for (Country country: countryResponse.results.getObjects()) {
            newList.add(country.getName());
        }
        ArrayList<String> temp = new ArrayList<>(newList);
        Collections.sort(temp);
        assertThat(temp).isEqualTo(newList);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - filter by country id's")
    @Test
    private void listCountryByIds() {

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setIdIn(countryId + "," + countryId2);
        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);

        // Total count = 2
        assertThat(countryResponse.results.getTotalCount()).isEqualTo(2);
        // object[0] country id = 1
        assertThat(countryResponse.results.getObjects().get(0).getId()).isEqualTo(countryId);
        // object[0] country id = 2
        assertThat(countryResponse.results.getObjects().get(1).getId()).isEqualTo(countryId2);
    }

    //TODO - Ask alon how to get country code dynamically (in order to assert).
    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - filter by current country")
    @Test
    private void listCountryByCurrentLocation() {

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setIpEqualCurrent(true);
        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);
        assertThat(countryResponse.results.getTotalCount()).isEqualTo(1);
    }

    // TODO - Remove hardcoded values
    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - filter by specific ip")
    @Test
    private void listCountryBySepcificIp() {
        String usaIp = "74.240.65.157";
        String usaCountryCode = "US";
        String currency = "USD";
        String currencySign = "$";
        String langCode = DEFAULT_LANGUAGE;

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setIpEqual(usaIp);
        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);

        assertThat(countryResponse.results.getTotalCount()).isEqualTo(1);
        assertThat(countryResponse.results.getObjects().get(0).getCode()).isEqualTo(usaCountryCode);
        assertThat(countryResponse.results.getObjects().get(0).getCurrency()).isEqualTo(currency);
        assertThat(countryResponse.results.getObjects().get(0).getMainLanguageCode()).isEqualTo(langCode);
        assertThat(countryResponse.results.getObjects().get(0).getCurrencySign()).isEqualTo(currencySign);
    }

    // Error validations

    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - 4025 - CountryNotFound")
    @Test
    private void invalidCountryIp() {

        String invalidIp = "8.8";

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setIpEqual(invalidIp);
        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);

        // Assert error code
        assertThat(countryResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(4025).getCode());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - Invalid country id")
    @Test
    private void invalidCountryId() {

        String invalidCountryId = "88888";

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setIdIn(invalidCountryId);
        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);

        // Assert error code
        assertThat(countryResponse.results.getTotalCount()).isEqualTo(0);
    }


    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - 500038 - Only one of IpEqual or IpEqualCurrent can be used, not both of them")
    @Test
    private void ipEqualandUpEqualCurrent() {

        String iranIp = "5.232.189.218";

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setIpEqual(iranIp);
        countryFilter.setIpEqualCurrent(true);

        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);

        // Assert error code
        assertThat(countryResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500038).getCode());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("country/action/list - 500038 - Only one of IdIn or IpEqualCurrent can be used, not both of them")
    @Test
    private void idInandUpEqualCurrent() {

        CountryFilter countryFilter = new CountryFilter();
        countryFilter.setIdIn(countryId  + "" + countryId2);
        countryFilter.setIpEqualCurrent(true);

        ListCountryBuilder listCountryBuilder = new ListCountryBuilder(countryFilter)
                .setKs(getOperatorKs());
        Response<ListResponse<Country>> countryResponse = executor.executeSync(listCountryBuilder);

        // Assert error code
        assertThat(countryResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500038).getCode());
    }
}
