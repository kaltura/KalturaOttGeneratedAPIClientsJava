package com.kaltura.client.test.tests.servicesTests.ottUserTests;

import com.kaltura.client.test.servicesImpl.OttUserServiceImpl;
import com.kaltura.client.test.tests.BaseTest;
import com.kaltura.client.types.OTTUser;
import com.kaltura.client.types.OTTUserDynamicData;
import com.kaltura.client.types.StringValue;
import com.kaltura.client.utils.response.base.Response;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.kaltura.client.test.Properties.GLOBAL_USER_PASSWORD;
import static com.kaltura.client.test.Properties.PARTNER_ID;
import static com.kaltura.client.test.servicesImpl.OttUserServiceImpl.register;
import static com.kaltura.client.test.utils.OttUserUtils.generateOttUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UpdateDynamicDataTests extends BaseTest {

    private OTTUser user;
    private String password = GLOBAL_USER_PASSWORD;

    private Response<OTTUserDynamicData> ottUserDynamicDataResponse;

    @BeforeClass
    private void ottUser_updateDynamicData_tests_setup() {
        Response<OTTUser> ottUserResponse = register(PARTNER_ID, generateOttUser(), password);
        user = ottUserResponse.results;
    }

    @Description("ottUser/action/updateDynamicData - updateDynamicData")
    @Test
    private void updateDynamicData() {
        StringValue value = new StringValue();
        value.setValue("value1");

        ottUserDynamicDataResponse = OttUserServiceImpl.updateDynamicData(administratorKs, Optional.of(Integer.valueOf(user.getId())), "key1", value);
        assertThat(ottUserDynamicDataResponse.error).isNull();
        assertThat(ottUserDynamicDataResponse.results.getValue().getValue()).isEqualTo("value1");
        assertThat(ottUserDynamicDataResponse.results.getKey()).isEqualTo("key1");
    }
}
