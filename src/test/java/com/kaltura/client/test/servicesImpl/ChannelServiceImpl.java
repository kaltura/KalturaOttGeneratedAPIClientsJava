package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.ChannelService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Channel;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.ChannelService.*;
import static org.awaitility.Awaitility.await;

public class ChannelServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Channel> channelResponse;
    private static Response<Boolean> booleanResponse;

    // add
    public static Response<Channel> add(Client client, Channel channel) {
        AddChannelBuilder addChannelBuilder = ChannelService.add(channel)
                .setCompletion((ApiCompletion<Channel>) result -> {
                    channelResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(addChannelBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return channelResponse;
    }

    // delete
    public static Response<Boolean> delete(Client client, int channelId) {
        DeleteChannelBuilder deleteChannelBuilder = ChannelService.delete(channelId)
                .setCompletion((ApiCompletion<Boolean>) result -> {
                    booleanResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(deleteChannelBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return booleanResponse;
    }

    // get
    public static Response<Channel> get(Client client, int channelId) {
        GetChannelBuilder getChannelBuilder = ChannelService.get(channelId)
                .setCompletion((ApiCompletion<Channel>) result -> {
                    channelResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getChannelBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return channelResponse;
    }

    // update
    public static Response<Channel> update(Client client, int channelId, Channel channel) {
        UpdateChannelBuilder updateChannelBuilder = ChannelService.update(channelId, channel)
                .setCompletion((ApiCompletion<Channel>) result -> {
                    channelResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(updateChannelBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return channelResponse;
    }
}
