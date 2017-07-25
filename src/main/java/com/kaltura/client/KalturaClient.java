// ===================================================================================================
//                           _  __     _ _
//                          | |/ /__ _| | |_ _  _ _ _ __ _
//                          | ' </ _` | |  _| || | '_/ _` |
//                          |_|\_\__,_|_|\__|\_,_|_| \__,_|
//
// This file is part of the Kaltura Collaborative Media Suite which allows users
// to do with audio, video, and animation what Wiki platfroms allow them to do with
// text.
//
// Copyright (C) 2006-2017  Kaltura Inc.
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as
// published by the Free Software Foundation, either version 3 of the
// License, or (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// @ignore
// ===================================================================================================
package com.kaltura.client;
import com.kaltura.client.services.KalturaAnnouncementService;
import com.kaltura.client.services.KalturaAppTokenService;
import com.kaltura.client.services.KalturaAssetCommentService;
import com.kaltura.client.services.KalturaAssetService;
import com.kaltura.client.services.KalturaAssetFileService;
import com.kaltura.client.services.KalturaAssetHistoryService;
import com.kaltura.client.services.KalturaAssetStatisticsService;
import com.kaltura.client.services.KalturaBookmarkService;
import com.kaltura.client.services.KalturaCdnAdapterProfileService;
import com.kaltura.client.services.KalturaCdnPartnerSettingsService;
import com.kaltura.client.services.KalturaCDVRAdapterProfileService;
import com.kaltura.client.services.KalturaChannelService;
import com.kaltura.client.services.KalturaCompensationService;
import com.kaltura.client.services.KalturaConfigurationGroupService;
import com.kaltura.client.services.KalturaConfigurationGroupDeviceService;
import com.kaltura.client.services.KalturaConfigurationGroupTagService;
import com.kaltura.client.services.KalturaConfigurationsService;
import com.kaltura.client.services.KalturaCountryService;
import com.kaltura.client.services.KalturaCouponService;
import com.kaltura.client.services.KalturaCurrencyService;
import com.kaltura.client.services.KalturaDeviceBrandService;
import com.kaltura.client.services.KalturaDeviceFamilyService;
import com.kaltura.client.services.KalturaEngagementAdapterService;
import com.kaltura.client.services.KalturaEngagementService;
import com.kaltura.client.services.KalturaEntitlementService;
import com.kaltura.client.services.KalturaExportTaskService;
import com.kaltura.client.services.KalturaExternalChannelProfileService;
import com.kaltura.client.services.KalturaFavoriteService;
import com.kaltura.client.services.KalturaFollowTvSeriesService;
import com.kaltura.client.services.KalturaHomeNetworkService;
import com.kaltura.client.services.KalturaHouseholdService;
import com.kaltura.client.services.KalturaHouseholdDeviceService;
import com.kaltura.client.services.KalturaHouseholdLimitationsService;
import com.kaltura.client.services.KalturaHouseholdPaymentGatewayService;
import com.kaltura.client.services.KalturaHouseholdPaymentMethodService;
import com.kaltura.client.services.KalturaHouseholdPremiumServiceService;
import com.kaltura.client.services.KalturaHouseholdQuotaService;
import com.kaltura.client.services.KalturaHouseholdUserService;
import com.kaltura.client.services.KalturaInboxMessageService;
import com.kaltura.client.services.KalturaLanguageService;
import com.kaltura.client.services.KalturaLicensedUrlService;
import com.kaltura.client.services.KalturaMessageTemplateService;
import com.kaltura.client.services.KalturaMetaService;
import com.kaltura.client.services.KalturaNotificationService;
import com.kaltura.client.services.KalturaNotificationsPartnerSettingsService;
import com.kaltura.client.services.KalturaNotificationsSettingsService;
import com.kaltura.client.services.KalturaOssAdapterProfileService;
import com.kaltura.client.services.KalturaOttCategoryService;
import com.kaltura.client.services.KalturaOttUserService;
import com.kaltura.client.services.KalturaParentalRuleService;
import com.kaltura.client.services.KalturaPartnerConfigurationService;
import com.kaltura.client.services.KalturaPaymentGatewayProfileService;
import com.kaltura.client.services.KalturaPaymentMethodProfileService;
import com.kaltura.client.services.KalturaPersonalFeedService;
import com.kaltura.client.services.KalturaPinService;
import com.kaltura.client.services.KalturaPpvService;
import com.kaltura.client.services.KalturaPriceDetailsService;
import com.kaltura.client.services.KalturaPricePlanService;
import com.kaltura.client.services.KalturaProductPriceService;
import com.kaltura.client.services.KalturaPurchaseSettingsService;
import com.kaltura.client.services.KalturaRecommendationProfileService;
import com.kaltura.client.services.KalturaRecordingService;
import com.kaltura.client.services.KalturaRegionService;
import com.kaltura.client.services.KalturaRegistrySettingsService;
import com.kaltura.client.services.KalturaReminderService;
import com.kaltura.client.services.KalturaReportService;
import com.kaltura.client.services.KalturaSearchHistoryService;
import com.kaltura.client.services.KalturaSeriesRecordingService;
import com.kaltura.client.services.KalturaSessionService;
import com.kaltura.client.services.KalturaSocialActionService;
import com.kaltura.client.services.KalturaSocialCommentService;
import com.kaltura.client.services.KalturaSocialService;
import com.kaltura.client.services.KalturaSocialFriendActivityService;
import com.kaltura.client.services.KalturaSubscriptionService;
import com.kaltura.client.services.KalturaSubscriptionSetService;
import com.kaltura.client.services.KalturaSystemService;
import com.kaltura.client.services.KalturaTimeShiftedTvPartnerSettingsService;
import com.kaltura.client.services.KalturaTopicService;
import com.kaltura.client.services.KalturaTransactionService;
import com.kaltura.client.services.KalturaTransactionHistoryService;
import com.kaltura.client.services.KalturaUserAssetRuleService;
import com.kaltura.client.services.KalturaUserAssetsListItemService;
import com.kaltura.client.services.KalturaUserInterestService;
import com.kaltura.client.services.KalturaUserLoginPinService;
import com.kaltura.client.services.KalturaUserRoleService;

/**
 * This class was generated using clients-generator\exec.php
 * against an XML schema provided by Kaltura.
 * 
 * MANUAL CHANGES TO THIS CLASS WILL BE OVERWRITTEN.
 */

@SuppressWarnings("serial")
public class KalturaClient extends KalturaClientBase {
	
	public KalturaClient(KalturaConfiguration config) {
		super(config);
		
		this.setClientTag("java:17-07-25");
		this.setApiVersion("4.4.44.16544");
	}
	
	protected KalturaAnnouncementService announcementService;
	public KalturaAnnouncementService getAnnouncementService() {
		if(this.announcementService == null)
			this.announcementService = new KalturaAnnouncementService(this);
	
		return this.announcementService;
	}
	
	protected KalturaAppTokenService appTokenService;
	public KalturaAppTokenService getAppTokenService() {
		if(this.appTokenService == null)
			this.appTokenService = new KalturaAppTokenService(this);
	
		return this.appTokenService;
	}
	
	protected KalturaAssetCommentService assetCommentService;
	public KalturaAssetCommentService getAssetCommentService() {
		if(this.assetCommentService == null)
			this.assetCommentService = new KalturaAssetCommentService(this);
	
		return this.assetCommentService;
	}
	
	protected KalturaAssetService assetService;
	public KalturaAssetService getAssetService() {
		if(this.assetService == null)
			this.assetService = new KalturaAssetService(this);
	
		return this.assetService;
	}
	
	protected KalturaAssetFileService assetFileService;
	public KalturaAssetFileService getAssetFileService() {
		if(this.assetFileService == null)
			this.assetFileService = new KalturaAssetFileService(this);
	
		return this.assetFileService;
	}
	
	protected KalturaAssetHistoryService assetHistoryService;
	public KalturaAssetHistoryService getAssetHistoryService() {
		if(this.assetHistoryService == null)
			this.assetHistoryService = new KalturaAssetHistoryService(this);
	
		return this.assetHistoryService;
	}
	
	protected KalturaAssetStatisticsService assetStatisticsService;
	public KalturaAssetStatisticsService getAssetStatisticsService() {
		if(this.assetStatisticsService == null)
			this.assetStatisticsService = new KalturaAssetStatisticsService(this);
	
		return this.assetStatisticsService;
	}
	
	protected KalturaBookmarkService bookmarkService;
	public KalturaBookmarkService getBookmarkService() {
		if(this.bookmarkService == null)
			this.bookmarkService = new KalturaBookmarkService(this);
	
		return this.bookmarkService;
	}
	
	protected KalturaCdnAdapterProfileService cdnAdapterProfileService;
	public KalturaCdnAdapterProfileService getCdnAdapterProfileService() {
		if(this.cdnAdapterProfileService == null)
			this.cdnAdapterProfileService = new KalturaCdnAdapterProfileService(this);
	
		return this.cdnAdapterProfileService;
	}
	
	protected KalturaCdnPartnerSettingsService cdnPartnerSettingsService;
	public KalturaCdnPartnerSettingsService getCdnPartnerSettingsService() {
		if(this.cdnPartnerSettingsService == null)
			this.cdnPartnerSettingsService = new KalturaCdnPartnerSettingsService(this);
	
		return this.cdnPartnerSettingsService;
	}
	
	protected KalturaCDVRAdapterProfileService cDVRAdapterProfileService;
	public KalturaCDVRAdapterProfileService getCDVRAdapterProfileService() {
		if(this.cDVRAdapterProfileService == null)
			this.cDVRAdapterProfileService = new KalturaCDVRAdapterProfileService(this);
	
		return this.cDVRAdapterProfileService;
	}
	
	protected KalturaChannelService channelService;
	public KalturaChannelService getChannelService() {
		if(this.channelService == null)
			this.channelService = new KalturaChannelService(this);
	
		return this.channelService;
	}
	
	protected KalturaCompensationService compensationService;
	public KalturaCompensationService getCompensationService() {
		if(this.compensationService == null)
			this.compensationService = new KalturaCompensationService(this);
	
		return this.compensationService;
	}
	
	protected KalturaConfigurationGroupService configurationGroupService;
	public KalturaConfigurationGroupService getConfigurationGroupService() {
		if(this.configurationGroupService == null)
			this.configurationGroupService = new KalturaConfigurationGroupService(this);
	
		return this.configurationGroupService;
	}
	
	protected KalturaConfigurationGroupDeviceService configurationGroupDeviceService;
	public KalturaConfigurationGroupDeviceService getConfigurationGroupDeviceService() {
		if(this.configurationGroupDeviceService == null)
			this.configurationGroupDeviceService = new KalturaConfigurationGroupDeviceService(this);
	
		return this.configurationGroupDeviceService;
	}
	
	protected KalturaConfigurationGroupTagService configurationGroupTagService;
	public KalturaConfigurationGroupTagService getConfigurationGroupTagService() {
		if(this.configurationGroupTagService == null)
			this.configurationGroupTagService = new KalturaConfigurationGroupTagService(this);
	
		return this.configurationGroupTagService;
	}
	
	protected KalturaConfigurationsService configurationsService;
	public KalturaConfigurationsService getConfigurationsService() {
		if(this.configurationsService == null)
			this.configurationsService = new KalturaConfigurationsService(this);
	
		return this.configurationsService;
	}
	
	protected KalturaCountryService countryService;
	public KalturaCountryService getCountryService() {
		if(this.countryService == null)
			this.countryService = new KalturaCountryService(this);
	
		return this.countryService;
	}
	
	protected KalturaCouponService couponService;
	public KalturaCouponService getCouponService() {
		if(this.couponService == null)
			this.couponService = new KalturaCouponService(this);
	
		return this.couponService;
	}
	
	protected KalturaCurrencyService currencyService;
	public KalturaCurrencyService getCurrencyService() {
		if(this.currencyService == null)
			this.currencyService = new KalturaCurrencyService(this);
	
		return this.currencyService;
	}
	
	protected KalturaDeviceBrandService deviceBrandService;
	public KalturaDeviceBrandService getDeviceBrandService() {
		if(this.deviceBrandService == null)
			this.deviceBrandService = new KalturaDeviceBrandService(this);
	
		return this.deviceBrandService;
	}
	
	protected KalturaDeviceFamilyService deviceFamilyService;
	public KalturaDeviceFamilyService getDeviceFamilyService() {
		if(this.deviceFamilyService == null)
			this.deviceFamilyService = new KalturaDeviceFamilyService(this);
	
		return this.deviceFamilyService;
	}
	
	protected KalturaEngagementAdapterService engagementAdapterService;
	public KalturaEngagementAdapterService getEngagementAdapterService() {
		if(this.engagementAdapterService == null)
			this.engagementAdapterService = new KalturaEngagementAdapterService(this);
	
		return this.engagementAdapterService;
	}
	
	protected KalturaEngagementService engagementService;
	public KalturaEngagementService getEngagementService() {
		if(this.engagementService == null)
			this.engagementService = new KalturaEngagementService(this);
	
		return this.engagementService;
	}
	
	protected KalturaEntitlementService entitlementService;
	public KalturaEntitlementService getEntitlementService() {
		if(this.entitlementService == null)
			this.entitlementService = new KalturaEntitlementService(this);
	
		return this.entitlementService;
	}
	
	protected KalturaExportTaskService exportTaskService;
	public KalturaExportTaskService getExportTaskService() {
		if(this.exportTaskService == null)
			this.exportTaskService = new KalturaExportTaskService(this);
	
		return this.exportTaskService;
	}
	
	protected KalturaExternalChannelProfileService externalChannelProfileService;
	public KalturaExternalChannelProfileService getExternalChannelProfileService() {
		if(this.externalChannelProfileService == null)
			this.externalChannelProfileService = new KalturaExternalChannelProfileService(this);
	
		return this.externalChannelProfileService;
	}
	
	protected KalturaFavoriteService favoriteService;
	public KalturaFavoriteService getFavoriteService() {
		if(this.favoriteService == null)
			this.favoriteService = new KalturaFavoriteService(this);
	
		return this.favoriteService;
	}
	
	protected KalturaFollowTvSeriesService followTvSeriesService;
	public KalturaFollowTvSeriesService getFollowTvSeriesService() {
		if(this.followTvSeriesService == null)
			this.followTvSeriesService = new KalturaFollowTvSeriesService(this);
	
		return this.followTvSeriesService;
	}
	
	protected KalturaHomeNetworkService homeNetworkService;
	public KalturaHomeNetworkService getHomeNetworkService() {
		if(this.homeNetworkService == null)
			this.homeNetworkService = new KalturaHomeNetworkService(this);
	
		return this.homeNetworkService;
	}
	
	protected KalturaHouseholdService householdService;
	public KalturaHouseholdService getHouseholdService() {
		if(this.householdService == null)
			this.householdService = new KalturaHouseholdService(this);
	
		return this.householdService;
	}
	
	protected KalturaHouseholdDeviceService householdDeviceService;
	public KalturaHouseholdDeviceService getHouseholdDeviceService() {
		if(this.householdDeviceService == null)
			this.householdDeviceService = new KalturaHouseholdDeviceService(this);
	
		return this.householdDeviceService;
	}
	
	protected KalturaHouseholdLimitationsService householdLimitationsService;
	public KalturaHouseholdLimitationsService getHouseholdLimitationsService() {
		if(this.householdLimitationsService == null)
			this.householdLimitationsService = new KalturaHouseholdLimitationsService(this);
	
		return this.householdLimitationsService;
	}
	
	protected KalturaHouseholdPaymentGatewayService householdPaymentGatewayService;
	public KalturaHouseholdPaymentGatewayService getHouseholdPaymentGatewayService() {
		if(this.householdPaymentGatewayService == null)
			this.householdPaymentGatewayService = new KalturaHouseholdPaymentGatewayService(this);
	
		return this.householdPaymentGatewayService;
	}
	
	protected KalturaHouseholdPaymentMethodService householdPaymentMethodService;
	public KalturaHouseholdPaymentMethodService getHouseholdPaymentMethodService() {
		if(this.householdPaymentMethodService == null)
			this.householdPaymentMethodService = new KalturaHouseholdPaymentMethodService(this);
	
		return this.householdPaymentMethodService;
	}
	
	protected KalturaHouseholdPremiumServiceService householdPremiumServiceService;
	public KalturaHouseholdPremiumServiceService getHouseholdPremiumServiceService() {
		if(this.householdPremiumServiceService == null)
			this.householdPremiumServiceService = new KalturaHouseholdPremiumServiceService(this);
	
		return this.householdPremiumServiceService;
	}
	
	protected KalturaHouseholdQuotaService householdQuotaService;
	public KalturaHouseholdQuotaService getHouseholdQuotaService() {
		if(this.householdQuotaService == null)
			this.householdQuotaService = new KalturaHouseholdQuotaService(this);
	
		return this.householdQuotaService;
	}
	
	protected KalturaHouseholdUserService householdUserService;
	public KalturaHouseholdUserService getHouseholdUserService() {
		if(this.householdUserService == null)
			this.householdUserService = new KalturaHouseholdUserService(this);
	
		return this.householdUserService;
	}
	
	protected KalturaInboxMessageService inboxMessageService;
	public KalturaInboxMessageService getInboxMessageService() {
		if(this.inboxMessageService == null)
			this.inboxMessageService = new KalturaInboxMessageService(this);
	
		return this.inboxMessageService;
	}
	
	protected KalturaLanguageService languageService;
	public KalturaLanguageService getLanguageService() {
		if(this.languageService == null)
			this.languageService = new KalturaLanguageService(this);
	
		return this.languageService;
	}
	
	protected KalturaLicensedUrlService licensedUrlService;
	public KalturaLicensedUrlService getLicensedUrlService() {
		if(this.licensedUrlService == null)
			this.licensedUrlService = new KalturaLicensedUrlService(this);
	
		return this.licensedUrlService;
	}
	
	protected KalturaMessageTemplateService messageTemplateService;
	public KalturaMessageTemplateService getMessageTemplateService() {
		if(this.messageTemplateService == null)
			this.messageTemplateService = new KalturaMessageTemplateService(this);
	
		return this.messageTemplateService;
	}
	
	protected KalturaMetaService metaService;
	public KalturaMetaService getMetaService() {
		if(this.metaService == null)
			this.metaService = new KalturaMetaService(this);
	
		return this.metaService;
	}
	
	protected KalturaNotificationService notificationService;
	public KalturaNotificationService getNotificationService() {
		if(this.notificationService == null)
			this.notificationService = new KalturaNotificationService(this);
	
		return this.notificationService;
	}
	
	protected KalturaNotificationsPartnerSettingsService notificationsPartnerSettingsService;
	public KalturaNotificationsPartnerSettingsService getNotificationsPartnerSettingsService() {
		if(this.notificationsPartnerSettingsService == null)
			this.notificationsPartnerSettingsService = new KalturaNotificationsPartnerSettingsService(this);
	
		return this.notificationsPartnerSettingsService;
	}
	
	protected KalturaNotificationsSettingsService notificationsSettingsService;
	public KalturaNotificationsSettingsService getNotificationsSettingsService() {
		if(this.notificationsSettingsService == null)
			this.notificationsSettingsService = new KalturaNotificationsSettingsService(this);
	
		return this.notificationsSettingsService;
	}
	
	protected KalturaOssAdapterProfileService ossAdapterProfileService;
	public KalturaOssAdapterProfileService getOssAdapterProfileService() {
		if(this.ossAdapterProfileService == null)
			this.ossAdapterProfileService = new KalturaOssAdapterProfileService(this);
	
		return this.ossAdapterProfileService;
	}
	
	protected KalturaOttCategoryService ottCategoryService;
	public KalturaOttCategoryService getOttCategoryService() {
		if(this.ottCategoryService == null)
			this.ottCategoryService = new KalturaOttCategoryService(this);
	
		return this.ottCategoryService;
	}
	
	protected KalturaOttUserService ottUserService;
	public KalturaOttUserService getOttUserService() {
		if(this.ottUserService == null)
			this.ottUserService = new KalturaOttUserService(this);
	
		return this.ottUserService;
	}
	
	protected KalturaParentalRuleService parentalRuleService;
	public KalturaParentalRuleService getParentalRuleService() {
		if(this.parentalRuleService == null)
			this.parentalRuleService = new KalturaParentalRuleService(this);
	
		return this.parentalRuleService;
	}
	
	protected KalturaPartnerConfigurationService partnerConfigurationService;
	public KalturaPartnerConfigurationService getPartnerConfigurationService() {
		if(this.partnerConfigurationService == null)
			this.partnerConfigurationService = new KalturaPartnerConfigurationService(this);
	
		return this.partnerConfigurationService;
	}
	
	protected KalturaPaymentGatewayProfileService paymentGatewayProfileService;
	public KalturaPaymentGatewayProfileService getPaymentGatewayProfileService() {
		if(this.paymentGatewayProfileService == null)
			this.paymentGatewayProfileService = new KalturaPaymentGatewayProfileService(this);
	
		return this.paymentGatewayProfileService;
	}
	
	protected KalturaPaymentMethodProfileService paymentMethodProfileService;
	public KalturaPaymentMethodProfileService getPaymentMethodProfileService() {
		if(this.paymentMethodProfileService == null)
			this.paymentMethodProfileService = new KalturaPaymentMethodProfileService(this);
	
		return this.paymentMethodProfileService;
	}
	
	protected KalturaPersonalFeedService personalFeedService;
	public KalturaPersonalFeedService getPersonalFeedService() {
		if(this.personalFeedService == null)
			this.personalFeedService = new KalturaPersonalFeedService(this);
	
		return this.personalFeedService;
	}
	
	protected KalturaPinService pinService;
	public KalturaPinService getPinService() {
		if(this.pinService == null)
			this.pinService = new KalturaPinService(this);
	
		return this.pinService;
	}
	
	protected KalturaPpvService ppvService;
	public KalturaPpvService getPpvService() {
		if(this.ppvService == null)
			this.ppvService = new KalturaPpvService(this);
	
		return this.ppvService;
	}
	
	protected KalturaPriceDetailsService priceDetailsService;
	public KalturaPriceDetailsService getPriceDetailsService() {
		if(this.priceDetailsService == null)
			this.priceDetailsService = new KalturaPriceDetailsService(this);
	
		return this.priceDetailsService;
	}
	
	protected KalturaPricePlanService pricePlanService;
	public KalturaPricePlanService getPricePlanService() {
		if(this.pricePlanService == null)
			this.pricePlanService = new KalturaPricePlanService(this);
	
		return this.pricePlanService;
	}
	
	protected KalturaProductPriceService productPriceService;
	public KalturaProductPriceService getProductPriceService() {
		if(this.productPriceService == null)
			this.productPriceService = new KalturaProductPriceService(this);
	
		return this.productPriceService;
	}
	
	protected KalturaPurchaseSettingsService purchaseSettingsService;
	public KalturaPurchaseSettingsService getPurchaseSettingsService() {
		if(this.purchaseSettingsService == null)
			this.purchaseSettingsService = new KalturaPurchaseSettingsService(this);
	
		return this.purchaseSettingsService;
	}
	
	protected KalturaRecommendationProfileService recommendationProfileService;
	public KalturaRecommendationProfileService getRecommendationProfileService() {
		if(this.recommendationProfileService == null)
			this.recommendationProfileService = new KalturaRecommendationProfileService(this);
	
		return this.recommendationProfileService;
	}
	
	protected KalturaRecordingService recordingService;
	public KalturaRecordingService getRecordingService() {
		if(this.recordingService == null)
			this.recordingService = new KalturaRecordingService(this);
	
		return this.recordingService;
	}
	
	protected KalturaRegionService regionService;
	public KalturaRegionService getRegionService() {
		if(this.regionService == null)
			this.regionService = new KalturaRegionService(this);
	
		return this.regionService;
	}
	
	protected KalturaRegistrySettingsService registrySettingsService;
	public KalturaRegistrySettingsService getRegistrySettingsService() {
		if(this.registrySettingsService == null)
			this.registrySettingsService = new KalturaRegistrySettingsService(this);
	
		return this.registrySettingsService;
	}
	
	protected KalturaReminderService reminderService;
	public KalturaReminderService getReminderService() {
		if(this.reminderService == null)
			this.reminderService = new KalturaReminderService(this);
	
		return this.reminderService;
	}
	
	protected KalturaReportService reportService;
	public KalturaReportService getReportService() {
		if(this.reportService == null)
			this.reportService = new KalturaReportService(this);
	
		return this.reportService;
	}
	
	protected KalturaSearchHistoryService searchHistoryService;
	public KalturaSearchHistoryService getSearchHistoryService() {
		if(this.searchHistoryService == null)
			this.searchHistoryService = new KalturaSearchHistoryService(this);
	
		return this.searchHistoryService;
	}
	
	protected KalturaSeriesRecordingService seriesRecordingService;
	public KalturaSeriesRecordingService getSeriesRecordingService() {
		if(this.seriesRecordingService == null)
			this.seriesRecordingService = new KalturaSeriesRecordingService(this);
	
		return this.seriesRecordingService;
	}
	
	protected KalturaSessionService sessionService;
	public KalturaSessionService getSessionService() {
		if(this.sessionService == null)
			this.sessionService = new KalturaSessionService(this);
	
		return this.sessionService;
	}
	
	protected KalturaSocialActionService socialActionService;
	public KalturaSocialActionService getSocialActionService() {
		if(this.socialActionService == null)
			this.socialActionService = new KalturaSocialActionService(this);
	
		return this.socialActionService;
	}
	
	protected KalturaSocialCommentService socialCommentService;
	public KalturaSocialCommentService getSocialCommentService() {
		if(this.socialCommentService == null)
			this.socialCommentService = new KalturaSocialCommentService(this);
	
		return this.socialCommentService;
	}
	
	protected KalturaSocialService socialService;
	public KalturaSocialService getSocialService() {
		if(this.socialService == null)
			this.socialService = new KalturaSocialService(this);
	
		return this.socialService;
	}
	
	protected KalturaSocialFriendActivityService socialFriendActivityService;
	public KalturaSocialFriendActivityService getSocialFriendActivityService() {
		if(this.socialFriendActivityService == null)
			this.socialFriendActivityService = new KalturaSocialFriendActivityService(this);
	
		return this.socialFriendActivityService;
	}
	
	protected KalturaSubscriptionService subscriptionService;
	public KalturaSubscriptionService getSubscriptionService() {
		if(this.subscriptionService == null)
			this.subscriptionService = new KalturaSubscriptionService(this);
	
		return this.subscriptionService;
	}
	
	protected KalturaSubscriptionSetService subscriptionSetService;
	public KalturaSubscriptionSetService getSubscriptionSetService() {
		if(this.subscriptionSetService == null)
			this.subscriptionSetService = new KalturaSubscriptionSetService(this);
	
		return this.subscriptionSetService;
	}
	
	protected KalturaSystemService systemService;
	public KalturaSystemService getSystemService() {
		if(this.systemService == null)
			this.systemService = new KalturaSystemService(this);
	
		return this.systemService;
	}
	
	protected KalturaTimeShiftedTvPartnerSettingsService timeShiftedTvPartnerSettingsService;
	public KalturaTimeShiftedTvPartnerSettingsService getTimeShiftedTvPartnerSettingsService() {
		if(this.timeShiftedTvPartnerSettingsService == null)
			this.timeShiftedTvPartnerSettingsService = new KalturaTimeShiftedTvPartnerSettingsService(this);
	
		return this.timeShiftedTvPartnerSettingsService;
	}
	
	protected KalturaTopicService topicService;
	public KalturaTopicService getTopicService() {
		if(this.topicService == null)
			this.topicService = new KalturaTopicService(this);
	
		return this.topicService;
	}
	
	protected KalturaTransactionService transactionService;
	public KalturaTransactionService getTransactionService() {
		if(this.transactionService == null)
			this.transactionService = new KalturaTransactionService(this);
	
		return this.transactionService;
	}
	
	protected KalturaTransactionHistoryService transactionHistoryService;
	public KalturaTransactionHistoryService getTransactionHistoryService() {
		if(this.transactionHistoryService == null)
			this.transactionHistoryService = new KalturaTransactionHistoryService(this);
	
		return this.transactionHistoryService;
	}
	
	protected KalturaUserAssetRuleService userAssetRuleService;
	public KalturaUserAssetRuleService getUserAssetRuleService() {
		if(this.userAssetRuleService == null)
			this.userAssetRuleService = new KalturaUserAssetRuleService(this);
	
		return this.userAssetRuleService;
	}
	
	protected KalturaUserAssetsListItemService userAssetsListItemService;
	public KalturaUserAssetsListItemService getUserAssetsListItemService() {
		if(this.userAssetsListItemService == null)
			this.userAssetsListItemService = new KalturaUserAssetsListItemService(this);
	
		return this.userAssetsListItemService;
	}
	
	protected KalturaUserInterestService userInterestService;
	public KalturaUserInterestService getUserInterestService() {
		if(this.userInterestService == null)
			this.userInterestService = new KalturaUserInterestService(this);
	
		return this.userInterestService;
	}
	
	protected KalturaUserLoginPinService userLoginPinService;
	public KalturaUserLoginPinService getUserLoginPinService() {
		if(this.userLoginPinService == null)
			this.userLoginPinService = new KalturaUserLoginPinService(this);
	
		return this.userLoginPinService;
	}
	
	protected KalturaUserRoleService userRoleService;
	public KalturaUserRoleService getUserRoleService() {
		if(this.userRoleService == null)
			this.userRoleService = new KalturaUserRoleService(this);
	
		return this.userRoleService;
	}
	
	/**
	 * @param String $clientTag
	 */
	public void setClientTag(String clientTag){
		this.clientConfiguration.put("clientTag", clientTag);
	}
	
	/**
	 * @return String
	 */
	public String getClientTag(){
		if(this.clientConfiguration.containsKey("clientTag")){
			return (String) this.clientConfiguration.get("clientTag");
		}
		
		return null;
	}
	
	/**
	 * @param String $apiVersion
	 */
	public void setApiVersion(String apiVersion){
		this.clientConfiguration.put("apiVersion", apiVersion);
	}
	
	/**
	 * @return String
	 */
	public String getApiVersion(){
		if(this.clientConfiguration.containsKey("apiVersion")){
			return (String) this.clientConfiguration.get("apiVersion");
		}
		
		return null;
	}
	
	/**
	 * Impersonated partner id
	 * 
	 * @param Integer $partnerId
	 */
	public void setPartnerId(Integer partnerId){
		this.requestConfiguration.put("partnerId", partnerId);
	}
	
	/**
	 * Impersonated partner id
	 * 
	 * @return Integer
	 */
	public Integer getPartnerId(){
		if(this.requestConfiguration.containsKey("partnerId")){
			return (Integer) this.requestConfiguration.get("partnerId");
		}
		
		return null;
	}
	
	/**
	 * Impersonated user id
	 * 
	 * @param Integer $userId
	 */
	public void setUserId(Integer userId){
		this.requestConfiguration.put("userId", userId);
	}
	
	/**
	 * Impersonated user id
	 * 
	 * @return Integer
	 */
	public Integer getUserId(){
		if(this.requestConfiguration.containsKey("userId")){
			return (Integer) this.requestConfiguration.get("userId");
		}
		
		return null;
	}
	
	/**
	 * Content language
	 * 
	 * @param String $language
	 */
	public void setLanguage(String language){
		this.requestConfiguration.put("language", language);
	}
	
	/**
	 * Content language
	 * 
	 * @return String
	 */
	public String getLanguage(){
		if(this.requestConfiguration.containsKey("language")){
			return (String) this.requestConfiguration.get("language");
		}
		
		return null;
	}
	
	/**
	 * Content currency
	 * 
	 * @param String $currency
	 */
	public void setCurrency(String currency){
		this.requestConfiguration.put("currency", currency);
	}
	
	/**
	 * Content currency
	 * 
	 * @return String
	 */
	public String getCurrency(){
		if(this.requestConfiguration.containsKey("currency")){
			return (String) this.requestConfiguration.get("currency");
		}
		
		return null;
	}
	
	/**
	 * Kaltura API session
	 * 
	 * @param String $ks
	 */
	public void setKs(String ks){
		this.requestConfiguration.put("ks", ks);
	}
	
	/**
	 * Kaltura API session
	 * 
	 * @return String
	 */
	public String getKs(){
		if(this.requestConfiguration.containsKey("ks")){
			return (String) this.requestConfiguration.get("ks");
		}
		
		return null;
	}
	
	/**
	 * Kaltura API session
	 * 
	 * @param String $sessionId
	 */
	public void setSessionId(String sessionId){
		this.requestConfiguration.put("ks", sessionId);
	}
	
	/**
	 * Kaltura API session
	 * 
	 * @return String
	 */
	public String getSessionId(){
		if(this.requestConfiguration.containsKey("ks")){
			return (String) this.requestConfiguration.get("ks");
		}
		
		return null;
	}
	
	/**
	 * Clear all volatile configuration parameters
	 */
	protected void resetRequest(){
	}
}
