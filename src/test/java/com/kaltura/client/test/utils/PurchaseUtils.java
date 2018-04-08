package com.kaltura.client.test.utils;

import com.kaltura.client.enums.AssetReferenceType;
import com.kaltura.client.enums.ProductPriceOrderBy;
import com.kaltura.client.enums.TransactionType;
import com.kaltura.client.test.servicesImpl.AssetServiceImpl;
import com.kaltura.client.test.servicesImpl.ProductPriceServiceImpl;
import com.kaltura.client.test.servicesImpl.TransactionServiceImpl;
import com.kaltura.client.types.*;
import com.kaltura.client.utils.response.base.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PurchaseUtils {

    public static Map<String, String> purchasePpvDetailsMap;

    // TODO: 14/MAR/2018 add return
    public static void purchasePpv(String ks, Optional<Integer> mediaId, Optional<Integer> fileId, Optional<String> inCurrency) {
        purchasePpvDetailsMap = new HashMap<>();
        int paymentGatewayId = 0;

        int internalFileId =-1;
        if (fileId.isPresent()) {
            internalFileId = fileId.get();
        } else {
            Response<Asset> mediaAsset = AssetServiceImpl.get(ks, String.valueOf(mediaId.get()), AssetReferenceType.MEDIA);
            internalFileId = mediaAsset.results.getMediaFiles().get(0).getId();
        }

        ProductPriceFilter filter = new ProductPriceFilter();
        filter.setOrderBy(ProductPriceOrderBy.PRODUCT_ID_ASC.getValue());
        filter.setFileIdIn(String.valueOf(internalFileId));
        filter.setIsLowest(false);
        Response<ListResponse<ProductPrice>> productPriceListBeforePurchase = ProductPriceServiceImpl.list(ks, filter, inCurrency);
        String currency = inCurrency.isPresent() ? inCurrency.get() : productPriceListBeforePurchase.results.getObjects().get(0).getPrice().getCurrency();
        double price = productPriceListBeforePurchase.results.getObjects().get(0).getPrice().getAmount();
        String ppvModuleId = ((PpvPrice)productPriceListBeforePurchase.results.getObjects().get(0)).getPpvModuleId();

        Purchase purchase = new Purchase();
        purchase.setProductId(Integer.valueOf(ppvModuleId));
        purchase.setContentId(internalFileId);
        purchase.setCurrency(currency);
        purchase.setPrice(price);
        purchase.setProductType(Optional.of(TransactionType.PPV).get());
        purchase.setPaymentGatewayId(Optional.of(paymentGatewayId).get());
        TransactionServiceImpl.purchase(ks, purchase);

        // TODO: complete the purchase ppv test
        purchasePpvDetailsMap.put("price", String.valueOf(price));
        purchasePpvDetailsMap.put("currency", currency);
        purchasePpvDetailsMap.put("ppvModuleId", ppvModuleId);
        purchasePpvDetailsMap.put("fileId", String.valueOf(internalFileId));
    }
}
