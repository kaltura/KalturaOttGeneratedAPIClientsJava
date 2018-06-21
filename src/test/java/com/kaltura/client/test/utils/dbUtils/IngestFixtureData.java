package com.kaltura.client.test.utils.dbUtils;

import com.google.common.base.Strings;
import com.kaltura.client.Logger;
import com.kaltura.client.enums.SubscriptionDependencyType;
import com.kaltura.client.services.ChannelService;
import com.kaltura.client.services.ChannelService.GetChannelBuilder;
import com.kaltura.client.services.PriceDetailsService;
import com.kaltura.client.services.PriceDetailsService.ListPriceDetailsBuilder;
import com.kaltura.client.services.PricePlanService;
import com.kaltura.client.services.PricePlanService.ListPricePlanBuilder;
import com.kaltura.client.test.utils.BaseUtils;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import org.json.JSONArray;

import java.sql.SQLException;

import static com.kaltura.client.test.tests.BaseTest.*;
import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.ERROR_MESSAGE;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getJsonArrayFromQueryResult;

public class IngestFixtureData extends BaseUtils {

    public static PriceDetails loadPriceCode(Double priceAmount, String currency) {
        Logger.getLogger(IngestFixtureData.class).debug("loadPriceCode(): priceAmount = " + priceAmount + " currency = " + currency);
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_CODE_SELECT, true, partnerId, priceAmount, currency);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return null;
            }

            PriceDetailsFilter filter = new PriceDetailsFilter();
            filter.setIdIn(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
            ListPriceDetailsBuilder priceDetailsBuilder = PriceDetailsService.list(filter);
            Response<ListResponse<PriceDetails>> priceDetailsListResponse = executor.executeSync(priceDetailsBuilder.setKs(getOperatorKs()));

            return priceDetailsListResponse.results.getObjects().get(0);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("discount code can't be null");
        }
        return null;
    }

    public static DiscountModule loadDiscount(Double discountPrice, Double discountPercent) {
        Logger.getLogger(IngestFixtureData.class).debug("loadDiscount(): discountPrice = " + discountPrice + " discountPercent = " + discountPercent);
        DiscountModule result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(DISCOUNT_BY_PRICE_AND_PERCENT_SELECT, true, partnerId, discountPrice, discountPercent);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return result;
            }
            result = new DiscountModule();
            result.setToken(ID, String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
            result.setToken(NAME, jsonArray.getJSONObject(0).getString(CODE));
            result.setPercent(discountPercent);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("discount code can't be null");
        }
        return result;
    }

    public static PricePlan loadPricePlan(Double priceAmount, String currency, DiscountModule discountModule) {
        Logger.getLogger(IngestFixtureData.class).debug("loadPricePlan(): priceAmount = " + priceAmount + " currency = " + currency +
                " discountPercent = " + discountModule.getPercent());

        PricePlan pricePlan = null;

        try {
            PriceDetails priceCode = loadPriceCode(priceAmount, currency);
            if (priceCode == null) {
                return pricePlan;
            }

            JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_PLAN_SELECT, true, partnerId,
                    Integer.valueOf(discountModule.toParams().get("id").toString()), priceCode.getId());
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return pricePlan;
            }

            pricePlan = loadFirstPricePlanFromJsonArray(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("price plan data can't be null");
        }
        return pricePlan;
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

        PricePlan pricePlan = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(PRICE_PLAN_5_MIN_RENEW_SELECT, true, partnerId);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return pricePlan;
            }

            pricePlan = loadFirstPricePlanFromJsonArray(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("price plan data can't be null");
        }
        return pricePlan;
    }

    public static Subscription loadSharedCommonSubscription(PricePlan pricePlan) {
        Logger.getLogger(IngestFixtureData.class).debug("loadSharedCommonSubscription(): pricePlan id = " + pricePlan.getId());

        Subscription subscription = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(SUBSCRIPTION_SELECT, true, partnerId,
                    pricePlan.getId(), getSharedCommonDiscount().toParams().get("id"));
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return subscription;
            }

            subscription = new Subscription();
            subscription.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
            subscription.setName(jsonArray.getJSONObject(0).getString(NAME));
            subscription.setPricePlanIds(String.valueOf(jsonArray.getJSONObject(0).getLong(PRICE_PLAN_ID)));
            subscription.setIsRenewable(false);
            subscription.setDependencyType(SubscriptionDependencyType.BASE);
            // TODO: add more data in case it needed
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("subscription data can't be null");
        }
        return subscription;
    }

    public static Collection loadSharedCommonCollection(PricePlan pricePlan) {
        Logger.getLogger(IngestFixtureData.class).debug("loadSharedCommonCollection(): price_id = " + pricePlan.getPriceDetailsId() +
                "discount_id = " + pricePlan.getDiscountId() + "usage_module_id = " + pricePlan.getId());

        Collection collection = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(COLLECTION_SELECT, true, partnerId,
                    getSharedCommonDiscount().toParams().get("id"), pricePlan.getPriceDetailsId(), pricePlan.getId());
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return collection;
            }

            collection = new Collection();
            collection.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
            collection.setName(jsonArray.getJSONObject(0).getString(NAME));
            // TODO: add more data in case it needed
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("collection data can't be null");
        }
        return collection;
    }

    public static String getDiscount(String currency, int percent) {
        Logger.getLogger(IngestFixtureData.class).debug("getDiscount(): currency = " + currency + " percent = " + percent);
        String code = "";
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(DISCOUNT_BY_PERCENT_AND_CURRENCY, false, currency, percent, partnerId);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return null;
            }

            code = jsonArray.getJSONObject(0).getString(CODE);
            if ("".equals(code)) {
                throw new SQLException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error(CODE + " can't be null");
        }

        return code;
    }

    public static DiscountModule getDiscount(int percent) {
        Logger.getLogger(IngestFixtureData.class).debug("getDiscount(): percent = " + percent);
        DiscountModule result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(DISCOUNT_BY_PERCENT, false,
                    percent, partnerId);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return null;
            }

            result = new DiscountModule();
            result.setPercent((double) percent);
            if ("".equals(result.toParams().get(CODE))) {
                throw new SQLException();
            }
            result.setToken(CODE, jsonArray.getJSONObject(0).getString(CODE));
            result.setToken(ID, String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error(CODE + " can't be null");
        }
        return result;
    }

    public static int getEpgChannelId(String channelName) {
        Logger.getLogger(IngestFixtureData.class).debug("getEpgChannelId(): channelName = " + channelName);
        int epgChannelId = -1;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(EPG_CHANNEL_ID_SELECT, false, partnerId + 1, channelName);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                Logger.getLogger(IngestFixtureData.class).error(ERROR_MESSAGE);
                return epgChannelId;
            }
            epgChannelId = jsonArray.getJSONObject(0).getInt("id");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("epgChannelId can't be null");
        }

        return epgChannelId;
    }

    public static String getIngestItemUserData(int accountId) {
        Logger.getLogger(IngestFixtureData.class).debug("getIngestItemUserData(): accountId = " + accountId);
        String result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(INGEST_ITEMS_DATA_SELECT, false, accountId);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return result;
            }

            result = jsonArray.getJSONObject(0).getString(USERNAME) + ":" +
                    jsonArray.getJSONObject(0).getString(PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("data about ingest business module user can't be null");
        }

        return result;
    }

    public static Ppv loadSharedCommonPpv(PricePlan pricePlan) {
        Logger.getLogger(IngestFixtureData.class).debug("loadSharedCommonPpv(): pricePlan id = " + pricePlan.getId());

        Ppv ppv = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(PPV_SELECT_BY_PRICE_PLAN, true, partnerId,
                    pricePlan.getId());
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return ppv;
            }

            ppv = new Ppv();
            ppv.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)));
            ppv.setName(jsonArray.getJSONObject(0).getString(NAME));
            ppv.setIsSubscriptionOnly(jsonArray.getJSONObject(0).getInt(SUBSCRIPTION_ONLY) == 0);
            // TODO: add more data in case it needed
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("Ppv data can't be null");
        }
        return ppv;
    }

    public static Subscription loadShared5MinutesRenewableSubscription() {
        Logger.getLogger(IngestFixtureData.class).debug("loadShared5MinutesRenewableSubscription()");
        Subscription subscription = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(SUBSCRIPTION_5_MIN_RENEW_SELECT, true, partnerId);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return subscription;
            }

            subscription = new Subscription();
            subscription.setId(String.valueOf(jsonArray.getJSONObject(0).getInt(ID)).trim());
            subscription.setName(jsonArray.getJSONObject(0).getString(NAME));
            subscription.setPricePlanIds(String.valueOf(jsonArray.getJSONObject(0).getLong(PRICE_PLAN_ID)));
            subscription.setIsRenewable(false);
            subscription.setDependencyType(SubscriptionDependencyType.BASE);
            // TODO: add more data in case it needed
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("subscription data can't be null");
        }
        return subscription;
    }

    public static Channel getChannel(int id) {
        Logger.getLogger(IngestFixtureData.class).debug("getChannel(): id = " + id);
        Channel channel = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(CHANNEL_SELECT, id), true);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return channel;
            }

            GetChannelBuilder getChannelBuilder = ChannelService.get(jsonArray.getJSONObject(0).getInt(ID));
            Response<Channel> channelResponse = executor.executeSync(getChannelBuilder.setKs(getOperatorKs()));
            channel = channelResponse.results;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("channel data can't be null");
        }
        return channel;
    }

    public static String getAutomaticChannelExpression(int channelId) {
        Logger.getLogger(IngestFixtureData.class).debug("getAutomaticChannelExpression(): channelId = " + channelId);
        String result = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(CHANNEL_EXPRESSION_SELECT, channelId), true);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return result;
            }

            result = jsonArray.getJSONObject(0).getString(TAG_TYPE) + ":" +
                    jsonArray.getJSONObject(0).getString(TAG_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("channel tags data can't be null");
        }
        return result;
    }
}
