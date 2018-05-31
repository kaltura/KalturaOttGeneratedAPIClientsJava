package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.test.utils.HouseholdUtils;
import com.kaltura.client.test.utils.OttUserUtils;
import com.kaltura.client.types.Household;
import com.kaltura.client.types.HouseholdUser;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.get;
import static com.kaltura.client.test.utils.BaseUtils.getAPIExceptionFromList;
import static org.assertj.core.api.Assertions.assertThat;


public class OttUserUpdateTests extends BaseTest {

    private OTTUser user;
    private String userKs;

    @BeforeClass
    private void ottUser_updateTests_beforeClass() {
        // set household
        int numOfUsersInHousehold = 2;
        int numOfDevicesInHousehold = 1;
        Household household = HouseholdUtils.createHousehold(numOfUsersInHousehold, numOfDevicesInHousehold, true);
        HouseholdUser householdUser = HouseholdUtils.getMasterUserFromHousehold(household);
        userKs = OttUserUtils.getKs(Integer.parseInt(householdUser.getUserId()), null);

        // get ottUser
        user = executor.executeSync(get().setKs(userKs)).results;
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/update - update")
    @Test(enabled = true)
    private void update() {
        // update user info
        String newUserInfo = "newUserInfo";
        OTTUser updatedUser = new OTTUser();
        updatedUser.setFirstName(newUserInfo);
        updatedUser.setLastName(newUserInfo);
        updatedUser.setAddress(newUserInfo);
//        updatedUser.setDynamicData(dynamicDataMapBuilder("key", "value"));

        updatedUser = executor.executeSync(OttUserService.update(updatedUser).setKs(userKs)).results;

        // assert user new info
        assertThat(updatedUser.getFirstName()).isEqualTo(newUserInfo);
        assertThat(updatedUser.getLastName()).isEqualTo(newUserInfo);
        assertThat(updatedUser.getAddress()).isEqualTo(newUserInfo);
        assertThat(updatedUser).isEqualToIgnoringGivenFields(user, "firstName", "lastName", "address");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/update - with administratorKs")
    @Test(enabled = true)
    private void update_with_administratorKs() {
        // update user info
        String newUserInfo = "newUserInfo";
        OTTUser updatedUser = new OTTUser();
        updatedUser.setFirstName(newUserInfo);
        updatedUser.setLastName(newUserInfo);
        updatedUser.setAddress(newUserInfo);
//        updatedUser.setDynamicData(dynamicDataMapBuilder("key", "value"));

        updatedUser = executor.executeSync(OttUserService.update(updatedUser)
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId())))
                .results;

        // assert user new info
        assertThat(updatedUser.getFirstName()).isEqualTo(newUserInfo);
        assertThat(updatedUser.getLastName()).isEqualTo(newUserInfo);
        assertThat(updatedUser.getAddress()).isEqualTo(newUserInfo);
        assertThat(updatedUser).isEqualToIgnoringGivenFields(user, "firstName", "lastName", "address");
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("ottUser/action/update - update user externalId - error 500051")
    @Test(enabled = true)
    private void update_user_externalId() {
        // update user externalId
        String newExternalId = "newExternalId";
        OTTUser updatedUser = new OTTUser();
        updatedUser.setExternalId(newExternalId);

        Response<OTTUser> ottUserResponse = executor.executeSync(OttUserService.update(updatedUser).setKs(userKs));
        assertThat(ottUserResponse.results).isNull();
        assertThat(ottUserResponse.error.getCode()).isEqualTo(getAPIExceptionFromList(500051).getCode());
    }

    @AfterClass
    private void ottUser_updateTests_afterClass() {
        // cleanup - delete household
        executor.executeSync(HouseholdService.delete().setKs(userKs));
    }
}
