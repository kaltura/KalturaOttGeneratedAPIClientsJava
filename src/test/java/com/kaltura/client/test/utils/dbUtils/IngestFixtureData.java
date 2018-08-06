package com.kaltura.client.test.utils.dbUtils;

import com.kaltura.client.Logger;
import com.kaltura.client.enums.SubscriptionDependencyType;
import com.kaltura.client.services.PriceDetailsService;
import com.kaltura.client.services.PriceDetailsService.ListPriceDetailsBuilder;
import com.kaltura.client.services.PricePlanService;
import com.kaltura.client.services.PricePlanService.ListPricePlanBuilder;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.json.JSONArray;

import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getJsonArrayFromQueryResult;

public class IngestFixtureData extends BaseUtils {

    public static PriceDetails loadPriceCode(Double priceAmount, String currency) {
        Logger.getLogger(IngestFixtureData.class).debug("loadPriceCode(): priceAmount = " + priceAmount + " currency = " + currency);

        JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_CODE_SELECT, partnerId, priceAmount, currency);
        PriceDetailsFilter filter = new PriceDetailsFilter();
        filter.setIdIn(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
        ListPriceDetailsBuilder priceDetailsBuilder = PriceDetailsService.list(filter);
        Response<ListResponse<PriceDetails>> priceDetailsListResponse = executor.executeSync(priceDetailsBuilder.setKs(getOperatorKs()));

        return priceDetailsListResponse.results.getObjects().get(0);
    }

    public static DiscountModule loadDiscount(Double discountPrice, Double discountPercent) {
        Logger.getLogger(IngestFixtureData.class).debug("loadDiscount(): discountPrice = " + discountPrice + " discountPercent = " + discountPercent);

        JSONArray jsonArray = getJsonArrayFromQueryResult(DISCOUNT_BY_PRICE_AND_PERCENT_SELECT, partnerId, discountPrice, discountPercent);
        DiscountModule result = new DiscountModule();
        result.setToken(ID, String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
        result.setToken(NAME, jsonArray.getJSONObject(0).getString(CODE));
        result.setPercent(discountPercent);

        return result;
    }

    public static PricePlan loadPricePlan(Double priceAmount, String currency, DiscountModule discountModule) {
        Logger.getLogger(IngestFixtureData.class).debug("loadPricePlan(): priceAmount = " + priceAmount + " currency = " + currency +
                " discountPercent = " + discountModule.getPercent());

        PriceDetails priceCode = loadPriceCode(priceAmount, currency);
        JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_PLAN_SELECT, partnerId,
                Integer.valueOf(discountModule.toParams().get("id").toString()), priceCode.getId());

        return loadFirstPricePlanFromJsonArray(jsonArray);
    }

    static PricePlan loadFirstPricePlanFromJsonArray(JSONArray jsonArray) {
        PricePlanFilter filter = new PricePlanFilter();
        filter.setIdIn(String.valueOf(jsonArray.getJSONObject(0).getLong(ID)));
        ListPricePlanBuilder pricePlanBuilder = PricePlanService.list(filter);
        com.kaltura.client.utils.response.base.Response<ListResponse<PricePlan>> pricePlanListResponse =
                executor.executeSync(pricePlanBuilder.setKs(getOperatorKs()));
        return pricePlanListResponse.results.getObjects().get(0);
    }

    public static PricePlan load5MinRenewablePricePlan() {
        Logger.getLogger(IngestFixtureData.class).debug("load5MinRenewablePricePlan()");

        JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_PLAN_5_MIN_RENEW_SELECT, partnerId);

        return loadFirstPricePlanFromJsonArray(jsonArray);
    }

    public static Subscription loadSharedCommonSubscription(PricePlan pricePlan) {
        Logger.getLogger(IngestFixtureData.class).debug("loadSharedCommonSubscription(): pricePlan id = " + pricePlan.getId());

        JSONArray jsonArray = getJsonArrayFromQueryResult(SUBSCRIPTION_SELECT, partnerId,
                pricePlan.getId(), getSharedCommonDiscount().toParams().get("id"));

        Subscription subscription = new Subscription();
        subscription.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
        subscription.setMultilingualName(setTranslationToken(jsonArray.getJSONObject(0).getString(NAME)));
        subscription.setPricePlanIds(String.valueOf(jsonArray.getJSONObject(0).getLong(PRICE_PLAN_ID)));
        subscription.setIsRenewable(false);
        subscription.setDependencyType(SubscriptionDependencyType.BASE);

        return subscription;
    }

    public static Collection loadSharedCommonCollection(PricePlan pricePlan) {
        Logger.getLogger(IngestFixtureData.class).debug("loadSharedCommonCollection(): price_id = " + pricePlan.getPriceDetailsId() +
                "discount_id = " + pricePlan.getDiscountId() + "usage_module_id = " + pricePlan.getId());

        JSONArray jsonArray = getJsonArrayFromQueryResult(COLLECTION_SELECT, partnerId,
                getSharedCommonDiscount().toParams().get("id"), pricePlan.getPriceDetailsId(), pricePlan.getId());
        Collection collection = new Collection();
        collection.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
        collection.setMultilingualName(setTranslationToken(jsonArray.getJSONObject(0).getString(NAME)));
        // TODO: add more data in case it needed
        return collection;
    }

    public static String getDiscount(String currency, int percent) {
        Logger.getLogger(IngestFixtureData.class).debug("getDiscount(): currency = " + currency + " percent = " + percent);

        JSONArray jsonArray = getJsonArrayFromQueryResult(DISCOUNT_BY_PERCENT_AND_CURRENCY, currency, percent, partnerId);

        return jsonArray.getJSONObject(0).getString(CODE);
    }

    public static DiscountModule getDiscount(int percent) {
        Logger.getLogger(IngestFixtureData.class).debug("getDiscount(): percent = " + percent);

        JSONArray jsonArray = getJsonArrayFromQueryResult(DISCOUNT_BY_PERCENT,
                percent, partnerId);
        DiscountModule result = new DiscountModule();
        result.setPercent((double) percent);
        result.setToken(CODE, jsonArray.getJSONObject(0).getString(CODE));
        result.setToken(ID, String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));

        return result;
    }

    public static int getEpgChannelId(String channelName) {
        Logger.getLogger(IngestFixtureData.class).debug("getEpgChannelId(): channelName = " + channelName);

        JSONArray jsonArray = getJsonArrayFromQueryResult(EPG_CHANNEL_ID_SELECT, partnerId + 1, channelName);
        int epgChannelId = jsonArray.getJSONObject(0).getInt("id");

        return epgChannelId;
    }

    public static String getIngestItemUserData(int accountId) {
        Logger.getLogger(IngestFixtureData.class).debug("getIngestItemUserData(): accountId = " + accountId);

        JSONArray jsonArray = getJsonArrayFromQueryResult(INGEST_ITEMS_DATA_SELECT, accountId);

        return jsonArray.getJSONObject(0).getString(USERNAME) + ":" +
                jsonArray.getJSONObject(0).getString(PASSWORD);
    }

    public static Ppv loadSharedCommonPpv(PricePlan pricePlan) {
        Logger.getLogger(IngestFixtureData.class).debug("loadSharedCommonPpv(): pricePlan id = " + pricePlan.getId());

        JSONArray jsonArray = getJsonArrayFromQueryResult(PPV_SELECT_BY_PRICE_PLAN, partnerId,
                pricePlan.getId());
        Ppv ppv = new Ppv();
        ppv.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
        ppv.setName(jsonArray.getJSONObject(0).getString(NAME));
        ppv.setIsSubscriptionOnly(jsonArray.getJSONObject(0).getInt(SUBSCRIPTION_ONLY) == 0);
        // TODO: add more data in case it needed

        return ppv;
    }

    public static Subscription loadShared5MinutesRenewableSubscription() {
        Logger.getLogger(IngestFixtureData.class).debug("loadShared5MinutesRenewableSubscription()");

        JSONArray jsonArray = getJsonArrayFromQueryResult(SUBSCRIPTION_5_MIN_RENEW_SELECT, partnerId);
        Subscription subscription = new Subscription();
        subscription.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)).trim());
        subscription.setMultilingualName(setTranslationToken(jsonArray.getJSONObject(0).getString(NAME)));
        subscription.setPricePlanIds(String.valueOf(jsonArray.getJSONObject(0).getLong(PRICE_PLAN_ID)));
        subscription.setIsRenewable(false);
        subscription.setDependencyType(SubscriptionDependencyType.BASE);
        // TODO: add more data in case it needed

        return subscription;
    }

    public static String getAutomaticChannelExpression(int channelId) {
        Logger.getLogger(IngestFixtureData.class).debug("getAutomaticChannelExpression(): channelId = " + channelId);

        JSONArray jsonArray = getJsonArrayFromQueryResult(CHANNEL_EXPRESSION_SELECT, channelId);

        return jsonArray.getJSONObject(0).getInt(TAG_NAME) + ":" +
                jsonArray.getJSONObject(0).getString(TAG_VALUE);
    }
}
