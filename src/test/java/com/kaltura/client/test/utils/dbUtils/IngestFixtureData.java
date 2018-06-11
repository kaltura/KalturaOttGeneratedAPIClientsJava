package com.kaltura.client.test.utils.dbUtils;

import com.google.common.base.Strings;
import com.kaltura.client.Logger;
import com.kaltura.client.enums.SubscriptionDependencyType;
import com.kaltura.client.types.*;
import org.json.JSONArray;
import java.sql.SQLException;
import static com.kaltura.client.test.tests.BaseTest.partnerId;
import static com.kaltura.client.test.utils.dbUtils.DBConstants.*;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.ERROR_MESSAGE;
import static com.kaltura.client.test.utils.dbUtils.DBUtils.getJsonArrayFromQueryResult;

public class IngestFixtureData {

    public static PriceDetails loadPriceCode(Double priceAmount, String currency) {
        Logger.getLogger(IngestFixtureData.class).debug("loadPriceCode(): priceAmount = " + priceAmount + " currency = " + currency);
        PriceDetails result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(PRICE_CODE_SELECT, partnerId, priceAmount, currency), true);
            if (Strings.isNullOrEmpty(jsonArray.toString())) {
                return result;
            }

            result = new PriceDetails();
            result.setName(jsonArray.getJSONObject(0).getString(CODE));
            result.setId(jsonArray.getJSONObject(0).getInt(ID));
            Price price = new Price();
            price.setCurrency(currency);
            price.setAmount(priceAmount);
            result.setPrice(price);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(IngestFixtureData.class).error("discount code can't be null");
        }
        return result;
    }


    public static DiscountModule loadDiscount(Double discountPrice, Double discountPercent) {
        Logger.getLogger(IngestFixtureData.class).debug("loadDiscount(): discountPrice = " + discountPrice + " discountPercent = " + discountPercent);
        DiscountModule result = null;
        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(DISCOUNT_BY_PRICE_AND_PERCENT_SELECT,
                    partnerId, discountPrice, discountPercent), true);
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

    public static PricePlan loadPricePlan(Double priceAmount, String currency, Double discountPrice, Double discountPercent) {
        Logger.getLogger(IngestFixtureData.class).debug("loadPricePlan(): priceAmount = " + priceAmount + " currency = " + currency +
                " discountPrice = " + discountPrice + " discountPercent = " + discountPercent);

        PricePlan pricePlan = null;

        try {
            PriceDetails priceCode = loadPriceCode(priceAmount, currency);
            if (priceCode == null) {
                return pricePlan;
            }

            DiscountModule discountModule = loadDiscount(discountPrice, discountPercent);
            if (discountModule == null) {
                return pricePlan;
            }

            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(PRICE_PLAN_SELECT, partnerId,
                    Integer.valueOf(discountModule.toParams().get("id").toString()), priceCode.getId()), true);
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

    private static PricePlan loadFirstPricePlanFromJsonArray(JSONArray jsonArray) {
        PricePlan pricePlan = new PricePlan();
        pricePlan.setId(jsonArray.getJSONObject(0).getLong(ID));
        pricePlan.setName(jsonArray.getJSONObject(0).getString(NAME));
        pricePlan.setViewLifeCycle(jsonArray.getJSONObject(0).getInt(VIEW_LIFE_CYCLE_MINUTES));
        pricePlan.setFullLifeCycle(jsonArray.getJSONObject(0).getInt(FULL_LIFE_CYCLE_MINUTES));
        pricePlan.setMaxViewsNumber(jsonArray.getJSONObject(0).getInt(MAX_VIEWS_COUNT));
        pricePlan.setDiscountId(jsonArray.getJSONObject(0).getLong(INT_DISCOUNT_ID));
        pricePlan.setPriceDetailsId(jsonArray.getJSONObject(0).getLong(PRICING_ID));
        pricePlan.setIsRenewable(0 == jsonArray.getJSONObject(0).getInt(IS_RENEWED));
        pricePlan.setRenewalsNumber(jsonArray.getJSONObject(0).getInt(NUMBER_OF_REC_PERIODS));

        return pricePlan;
    }

    public static PricePlan load5MinRenewablePricePlan() {
        Logger.getLogger(IngestFixtureData.class).debug("load5MinRenewablePricePlan()");

        PricePlan pricePlan = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(PRICE_PLAN_5_MIN_RENEW_SELECT, partnerId), true);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(SUBSCRIPTION_SELECT, partnerId,
                    pricePlan.getId(), pricePlan.getDiscountId()), true);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(COLLECTION_SELECT, partnerId,
                    pricePlan.getDiscountId(), pricePlan.getPriceDetailsId(), pricePlan.getId()), true);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(DISCOUNT_BY_PERCENT_AND_CURRENCY, currency,
                    percent, partnerId), false);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(DISCOUNT_BY_PERCENT,
                    percent, partnerId), false);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(EPG_CHANNEL_ID_SELECT, partnerId + 1, channelName), false);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(INGEST_ITEMS_DATA_SELECT, accountId), false);
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
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(PPV_SELECT_BY_PRICE_PLAN, partnerId,
                    pricePlan.getId()), true);
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
        PricePlan pricePlan = load5MinRenewablePricePlan();
        if (pricePlan == null) {
            return null;
        }
        Subscription subscription = null;

        try {
            JSONArray jsonArray = getJsonArrayFromQueryResult(String.format(SUBSCRIPTION_5_MIN_RENEW_SELECT, partnerId,
                    pricePlan.getId()), true);
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
}
