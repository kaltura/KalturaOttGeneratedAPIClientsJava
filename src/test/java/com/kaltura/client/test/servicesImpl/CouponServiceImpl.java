package com.kaltura.client.test.servicesImpl;

import com.kaltura.client.Client;
import com.kaltura.client.services.CouponService;
import com.kaltura.client.test.TestAPIOkRequestsExecutor;
import com.kaltura.client.types.Coupon;
import com.kaltura.client.utils.response.base.ApiCompletion;
import com.kaltura.client.utils.response.base.Response;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.kaltura.client.services.CouponService.*;
import static org.awaitility.Awaitility.await;

public class CouponServiceImpl {

    private static final AtomicBoolean done = new AtomicBoolean(false);

    private static Response<Coupon> couponResponse;

    // get
    public static Response<Coupon> get(Client client, String code) {
        GetCouponBuilder getCouponBuilder = CouponService.get(code)
                .setCompletion((ApiCompletion<Coupon>) result -> {
                    couponResponse = result;
                    done.set(true);
                });

        TestAPIOkRequestsExecutor.getExecutor().queue(getCouponBuilder.build(client));
        await().untilTrue(done);
        done.set(false);

        return couponResponse;
    }
}
