package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.enums.HouseholdFrequencyType;
import com.kaltura.client.services.HouseholdService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Household;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.HouseholdService.*;
import static com.kaltura.client.services.HouseholdService.AddHouseholdBuilder;
import static com.kaltura.client.services.HouseholdService.DeleteHouseholdBuilder;
import static org.awaitility.Awaitility.await;

public class HouseholdServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Household> householdResponse;
    private static Response<Boolean> booleanResponse;


    // add
    public static Response<Household> add(Client client, Household household) {
        AddHouseholdBuilder addHouseholdBuilder = HouseholdService.add(household)
                .setCompletion((ApiCompletion<Household>) result -> {
                    householdResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, @Nullable int householdId) {
        DeleteHouseholdBuilder deleteHouseholdBuilder = HouseholdService.delete(householdId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<Household> get(Client client, @Nullable Integer id) {
        GetHouseholdBuilder getHouseholdBuilder;
        if (id == null) {
            getHouseholdBuilder = HouseholdService.get()
                    .setCompletion((ApiCompletion<Household>) result -> {
                        householdResponse = result;
                        done.set(true);
                    });
        } else {
            getHouseholdBuilder = HouseholdService.get(id)
                    .setCompletion((ApiCompletion<Household>) result -> {
                        householdResponse = result;
                        done.set(true);
                    });
        }

        TestAPIOkRequestsExecutor.getExecutor().queue(getHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdResponse;
    }

    // purge
    public static Response<Boolean> purge(Client client, int id) {
        PurgeHouseholdBuilder purgeHouseholdBuilder = HouseholdService.purge(id)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(purgeHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // resetFrequency
    public static Response<Household> resetFrequency(Client client, HouseholdFrequencyType frequencyType) {
        ResetFrequencyHouseholdBuilder resetFrequencyHouseholdBuilder = HouseholdService.resetFrequency(frequencyType)
                .setCompletion((ApiCompletion<Household>) result -> {
                    householdResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(resetFrequencyHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdResponse;
    }

    // resume
    public static Response<Boolean> resume(Client client) {
        ResumeHouseholdBuilder resumeHouseholdBuilder = HouseholdService.resume()
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(resumeHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // suspend
    public static Response<Boolean> suspend(Client client, @Nullable Integer roleId) {
        SuspendHouseholdBuilder suspendHouseholdBuilder = HouseholdService.suspend(roleId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(suspendHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // update
    public static Response<Household> update(Client client, Household household) {
        UpdateHouseholdBuilder updateHouseholdBuilder = HouseholdService.update(household)
                .setCompletion((ApiCompletion<Household>) result -> {
                    householdResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateHouseholdBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return householdResponse;
    }
}
