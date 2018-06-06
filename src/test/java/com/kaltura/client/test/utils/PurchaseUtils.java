package com.kaltura.client.test.utils;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.ProductPriceOrderBy;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.services.AssetService;
import com.kaltura.client.services.AssetService.GetAssetBuilder;
import com.kaltura.client.services.ProductPriceService;
import com.kaltura.client.services.ProductPriceService.ListProductPriceBuilder;
import com.kaltura.client.services.TransactionService;
import com.kaltura.client.services.TransactionService.PurchaseTransactionBuilder;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static com.kaltura.client.test.tests.BaseTest.executor;

public class PurchaseUtils {

    public static Map<String, String> purchasePpvDetailsMap;
    public static Map<String, String> purchaseSubscriptionDetailsMap;
    public static Map<String, String> purchaseCollectionDetailsMap;

    private static Response<ListResponse<ProductPrice>> productPriceResponse;
    private static Response<Asset> assetResponse;

    public static Response<Transaction> purchasePpv(String ks, Optional<Integer> mediaId, Optional<Integer> fileId, Optional<String> purchaseCurrency) {
        purchasePpvDetailsMap = new HashMap<>();

        int internalFileId;
        if (fileId.isPresent()) {
            internalFileId = fileId.get();
        } else {
            GetAssetBuilder getAssetBuilder = AssetService.get(String.valueOf(mediaId.get()), AssetReferenceType.MEDIA).setKs(ks);
            assetResponse = executor.executeSync(getAssetBuilder);
            internalFileId = assetResponse.results.getMediaFiles().get(0).getId();
        }

        ProductPriceFilter filter = new ProductPriceFilter();
        filter.setOrderBy(ProductPriceOrderBy.PRODUCT_ID_ASC.getValue());
        filter.setFileIdIn(String.valueOf(internalFileId));
        filter.setIsLowest(false);

        ListProductPriceBuilder listProductPriceBuilder = purchaseCurrency.isPresent()
                ? ProductPriceService.list(filter).setKs(ks).setCurrency(purchaseCurrency.get())
                : ProductPriceService.list(filter).setKs(ks);
        productPriceResponse = executor.executeSync(listProductPriceBuilder);

        double price = productPriceResponse.results.getObjects().get(0).getPrice().getAmount();
        String ppvModuleId = ((PpvPrice)productPriceResponse.results.getObjects().get(0)).getPpvModuleId();

        Purchase purchase = new Purchase();
        purchase.setProductId(Integer.valueOf(ppvModuleId));
        purchase.setContentId(internalFileId);
        String currency = purchaseCurrency.orElse(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency());
        purchase.setCurrency(currency);
        purchase.setPrice(price);
        purchase.setProductType(Optional.of(TransactionType.PPV).get());

        PurchaseTransactionBuilder transactionBuilder = TransactionService.purchase(purchase).setKs(ks);
        Response<Transaction> transactionResponse = executor.executeSync(transactionBuilder);

        // TODO: complete the purchase ppv test
        purchasePpvDetailsMap.put("price", String.valueOf(price));
        purchasePpvDetailsMap.put("currency", currency);
        purchasePpvDetailsMap.put("ppvModuleId", ppvModuleId);
        purchasePpvDetailsMap.put("fileId", String.valueOf(internalFileId));

        return transactionResponse;
    }

    public static Response<Transaction> purchaseSubscription(String ks, int subscriptionId, Optional<String> currency) {
        purchaseSubscriptionDetailsMap = new HashMap<>();

        ProductPriceFilter filter = new ProductPriceFilter();
        filter.setSubscriptionIdIn(String.valueOf(subscriptionId));
        filter.setIsLowest(false);

        ListProductPriceBuilder listProductPriceBuilder = currency.isPresent()
                ? ProductPriceService.list(filter).setKs(ks).setCurrency(currency.get())
                : ProductPriceService.list(filter).setKs(ks);
        productPriceResponse = executor.executeSync(listProductPriceBuilder);

        String currencyValue = currency.orElse(productPriceResponse.results.getObjects().get(0).getPrice().getCurrency());
        double price = productPriceResponse.results.getObjects().get(0).getPrice().getAmount();

        Purchase purchase = new Purchase();
        purchase.setProductId(subscriptionId);
        purchase.setContentId(0);
        purchase.setCurrency(currencyValue);
        purchase.setPrice(price);
        purchase.setProductType(Optional.of(TransactionType.SUBSCRIPTION).get());

        PurchaseTransactionBuilder purchaseTransactionBuilder = TransactionService.purchase(purchase).setKs(ks);
        Response<Transaction> transactionResponse = executor.executeSync(purchaseTransactionBuilder);

        // TODO: complete the purchase subscription test
        purchaseSubscriptionDetailsMap.put("price", String.valueOf(price));
        purchaseSubscriptionDetailsMap.put("currency", currencyValue);

        return transactionResponse;
    }

    public static Response<Transaction> purchaseCollection (String ks, int collectionId){
        purchaseCollectionDetailsMap = new HashMap<>();

        ProductPriceFilter productPriceFilter = new ProductPriceFilter();
        productPriceFilter.setCollectionIdIn(String.valueOf(collectionId));

        ListProductPriceBuilder listProductPriceBuilder = ProductPriceService.list(productPriceFilter).setKs(ks);
        productPriceResponse = executor.executeSync(listProductPriceBuilder);

        String collectionPriceCurrency = productPriceResponse.results.getObjects().get(0).getPrice().getCurrency();
        double collectionPriceAmount = productPriceResponse.results.getObjects().get(0).getPrice().getAmount();

        Purchase purchaseRequest = new Purchase();
        purchaseRequest.setCurrency(collectionPriceCurrency);
        purchaseRequest.setPrice(collectionPriceAmount);
        purchaseRequest.setContentId(0);
        purchaseRequest.setProductId(collectionId);
        purchaseRequest.setProductType(TransactionType.COLLECTION);

        PurchaseTransactionBuilder purchaseTransactionBuilder = TransactionService.purchase(purchaseRequest).setKs(ks);
        Response<Transaction> transactionResponse = executor.executeSync(purchaseTransactionBuilder);

        purchaseCollectionDetailsMap.put("price", String.valueOf(collectionPriceAmount));
        purchaseCollectionDetailsMap.put("currency", collectionPriceCurrency);

        return transactionResponse;
    }
}
