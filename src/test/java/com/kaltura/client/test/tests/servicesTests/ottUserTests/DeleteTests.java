package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.HouseholdServiceImpl;
import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import com.sun.org.glassfish.gmbal.Description;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.get;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static com.kaltura.client.test.utils.HouseholdUtils.getDefaultUserFromHousehold;
import static com.kaltura.client.test.utils.HouseholdUtils.getMasterUserFromHousehold;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTests extends BaseTest {

    private Household household;
    private Response<Boolean> booleanResponse;

    @BeforeClass
    private void ottUser_delete_tests_setup() {
        household = HouseholdUtils.createHouseHold(2, 0, false);
    }

    @Description("ottUser/action/delete - delete")
    @Test
    private void delete() {
        Response<OTTUser> ottUserResponse = register(PARTNER_ID, generateOttUser(), GLOBAL_USER_PASSWORD);
        OTTUser user = ottUserResponse.results;

        Response<Boolean> booleanResponse = OttUserServiceImpl.delete(administratorKs, Optional.of(Integer.valueOf(user.getId())));
        boolean result = booleanResponse.results;
        assertThat(result).isTrue();

        ottUserResponse = get(administratorKs, Optional.of(Integer.valueOf(user.getId())));
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500004).getCode());
        assertThat(ottUserResponse.results).isNull();
    }

    @Description("ottUser/action/delete - delete master user")
    @Test(enabled = true)
    private void delete_master_user() {
        HouseholdUser masterUser = getMasterUserFromHousehold(household);
        booleanResponse = OttUserServiceImpl.delete(administratorKs, Optional.of(Integer.valueOf(masterUser.getUserId())));

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2031).getCode());
    }
    
    @Description("ottUser/action/delete - delete default user")
    @Test(enabled = true)
    private void delete_default_user() {
        HouseholdUser defaultUser = getDefaultUserFromHousehold(household);
        booleanResponse = OttUserServiceImpl.delete(administratorKs, Optional.of(Integer.valueOf(defaultUser.getUserId())));

        assertThat(booleanResponse.results).isNull();
        assertThat(booleanResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(2030).getCode());
    }

    @AfterClass
    private void ottUser_delete_tests_tearDown() {
        HouseholdServiceImpl.delete(administratorKs, Math.toIntExact(household.getId()));
    }

}
