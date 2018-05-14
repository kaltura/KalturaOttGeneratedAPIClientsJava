package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.services.OttUserService;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.kaltura.client.services.OttUserService.*;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static com.kaltura.client.test.utils.OttUserUtils.getKs;
import static org.assertj.core.api.Assertions.assertThat;

public class OttUserGetEncryptedUserIdTests extends BaseTest {

    private OTTUser user;

    @BeforeClass
    private void ottUser_getEncryptedUserId_tests_setup() {
        // register user
        user = executor.executeSync(register(partnerId, generateOttUser(), defaultUserPassword)).results;
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/getEncryptedUserId - getEncryptedUserId with super user ks")
    @Test
    private void getEncryptedUserId_with_superUserKs () {
        GetEncryptedUserIdOttUserBuilder getEncryptedUserIdOttUserBuilder = OttUserService.getEncryptedUserId()
                .setKs(getAdministratorKs())
                .setUserId(Integer.valueOf(user.getId()));
        Response<StringValue> stringValueResponse = executor.executeSync(getEncryptedUserIdOttUserBuilder);

        assertThat(stringValueResponse.error).isNull();
        assertThat(stringValueResponse.results.getValue()).isNotNull();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("ottUser/action/getEncryptedUserId")
    @Test
    private void getEncryptedUserId() {
        String userKs = getKs(Integer.parseInt(user.getId()), null);
        GetEncryptedUserIdOttUserBuilder getEncryptedUserIdOttUserBuilder = OttUserService.getEncryptedUserId()
                .setKs(userKs);
        Response<StringValue> stringValueResponse = executor.executeSync(getEncryptedUserIdOttUserBuilder);

        assertThat(stringValueResponse.error).isNull();
        assertThat(stringValueResponse.results.getValue()).isNotNull();
    }
}
