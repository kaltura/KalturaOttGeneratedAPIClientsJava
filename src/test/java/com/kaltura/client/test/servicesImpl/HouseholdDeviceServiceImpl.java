package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.DeviceStatus;
import com.kaltura.client.services.HouseholdDeviceService;
import com.kaltura.client.services.HouseholdDeviceService.*;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.awaitility.Awaitility.await;

public class HouseholdDeviceServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<HouseholdDevice> householdDeviceResponse;
    private static Response<ListResponse<HouseholdDevice>> householdDevicesListResponse;
    private static Response<Boolean> booleanResponse;
    private static Response<DevicePin> devicePinResponse;
    private static Response<LoginResponse> loginResponse;


    // add
    public static Response<HouseholdDevice> add(Client client, HouseholdDevice householdDevice) {
        AddHouseholdDeviceBuilder addHouseholdDeviceBuilder = HouseholdDeviceService.add(householdDevice)
                .setCompletion((ApiCompletion<HouseholdDevice>) result -> {
                    householdDeviceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDeviceResponse;
    }

    // list
    public static Response<ListResponse<HouseholdDevice>> list(Client client, HouseholdDeviceFilter householdDeviceFilter) {
        ListHouseholdDeviceBuilder listHouseholdDeviceBuilder = HouseholdDeviceService.list(householdDeviceFilter)
                .setCompletion((ApiCompletion<ListResponse<HouseholdDevice>>) result -> {
                    householdDevicesListResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(listHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDevicesListResponse;
    }

    // addByPin
    public static Response<HouseholdDevice> addByPin(Client client, String deviceName, String pin) {
        AddByPinHouseholdDeviceBuilder addByPinHouseholdDeviceBuilder = HouseholdDeviceService.addByPin(deviceName, pin)
                .setCompletion((ApiCompletion<HouseholdDevice>) result -> {
                    householdDeviceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addByPinHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDeviceResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, String udid) {
        DeleteHouseholdDeviceBuilder deleteHouseholdDeviceBuilder = HouseholdDeviceService.delete(udid)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // generatePin
    public static Response<DevicePin> generatePin(Client client, String udid, int brandId) {
        GeneratePinHouseholdDeviceBuilder generatePinHouseholdDeviceBuilder = HouseholdDeviceService.generatePin(udid, brandId)
                .setCompletion((ApiCompletion<DevicePin>) result -> {
                    devicePinResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(generatePinHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return devicePinResponse;
    }

    // get
    public static Response<HouseholdDevice> get(Client client) {
        GetHouseholdDeviceBuilder getHouseholdDeviceBuilder = HouseholdDeviceService.get()
                .setCompletion((ApiCompletion<HouseholdDevice>) result -> {
                    householdDeviceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDeviceResponse;
    }

    // loginWithPin
    public static Response<LoginResponse> loginWithPin(Client client, int partnerId, String pin, @Nullable String udid) {
        LoginWithPinHouseholdDeviceBuilder loginWithPinHouseholdDeviceBuilder = HouseholdDeviceService.loginWithPin(partnerId, pin, udid)
                .setCompletion((ApiCompletion<LoginResponse>) result -> {
                    loginResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(loginWithPinHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return loginResponse;
    }

    // update
    public static Response<HouseholdDevice> update(Client client, String udid, HouseholdDevice householdDevice) {
        UpdateHouseholdDeviceBuilder updateHouseholdDeviceBuilder = HouseholdDeviceService.update(udid, householdDevice)
                .setCompletion((ApiCompletion<HouseholdDevice>) result -> {
                    householdDeviceResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdDeviceResponse;
    }

    // updateStatus
    public static Response<Boolean> updateStatus(Client client, String udid, DeviceStatus deviceStatus) {
        UpdateStatusHouseholdDeviceBuilder updateStatusHouseholdDeviceBuilder = HouseholdDeviceService.updateStatus(udid, deviceStatus)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateStatusHouseholdDeviceBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }
}
