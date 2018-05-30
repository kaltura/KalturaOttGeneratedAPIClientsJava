# PHASE 1
Migrate services tests from ReadyAPI

# PHASE 2
Migrate features tests from ReadyAPI


### Services List:

| Service                      | Status |
|------------------------------|--------|
| announcement                 |        |
| appToken                     |        |
| asset                        |        |
| assetComment                 |        |
| assetFile                    |        |
| assetHistory                 |        |
| assetRule                    |        |
| assetStatistics              |        |
| assetUserRule                |        |
| bookmark                     |        |
| cDVRAdapterProfile           |        |
| cdnAdapterProfile            |        |
| cdnPartnerSettings           |        |
| channel                      |        |
| collection                   |        |
| compensation                 |        |
| configurationGroup           |        |
| configurationGroupDevice     |        |
| configurationGroupTag        |        |
| configurations               |        |
| country                      |        |
| coupon                       |        |
| couponsGroup                 |        |
| currency                     |        |
| deviceBrand                  |        |
| deviceFamily                 |        |
| email                        |        |
| engagement                   |        |
| engagementAdapter            |        |
| entitlement                  |        |
| exportTask                   |        |
| externalChannelProfile       |        |
| favorite                     |        |
| followTvSeries               |        |
| homeNetwork                  |        |
| household                    | Alon - in progress, actions: add - done, delete - done, get - done, suspend - in progress, purge, resume, resetFrequency, update |
| householdDevice              |        |
| householdLimitations         |        |
| householdPaymentGateway      |        |
| householdPaymentMethod       |        |
| householdPremiumService      |        |
| householdQuota               |        |
| householdUser                |        |
| inboxMessage                 |        |
| language                     |        |
| licensedUrl                  |        |
| messageTemplate              |        |
| meta                         |        |
| notification                 |        |
| notificationsPartnerSettings |        |
| notificationsSettings        |        |
| ossAdapterProfile            |        |
| ottCategory                  |        |
| ottUser                      | Done   |
| parentalRule                 |        |
| partnerConfiguration         |        |
| paymentGatewayProfile        |        |
| paymentMethodProfile         |        |
| personalFeed                 |        |
| pin                          |        |
| ppv                          |        |
| priceDetails                 |        |
| pricePlan                    |        |
| productPrice                 | Max - in progress |
| purchaseSettings             |        |
| recommendationProfile        |        |
| recording                    |        |
| region                       |        |
| registrySettings             |        |
| reminder                     |        |
| report                       |        |
| searchHistory                |        |
| seriesRecording              |        |
| session                      |        |
| social                       |        |
| socialAction                 |        |
| socialComment                |        |
| socialFriendActivity         |        |
| subscription                 |        |
| subscriptionSet              |        |
| system                       |        |
| timeShiftedTvPartnerSettings |        |
| topic                        |        |
| transaction                  |        |
| transactionHistory           | Michael - in progress |
| unifiedPayment               |        |
| userAssetRule                |        |
| userAssetsListItem           |        |
| userInterest                 |        |
| userLoginPin                 |        |
| userRole                     |        |


### Features List:

| Features                                                                                              | Status |
|-------------------------------------------------------------------------------------------------------|--------|
| Thor - Search recordings                                                                              |        |
| Thor - New DMS                                                                                        |        |
| Thor - C-DVR bookmarks                                                                                |        |
| Thor - Social settings and actions                                                                    |        |
| Thor - Social feed                                                                                    |        |
| Thor - Verify playback license for recordings                                                         |        |
| Ultron - Delete_close household [BEO-2723]                                                            |        |
| Ultron - Automated email configuration                                                                |        |
| Ultron - Purchase email to be sent to the HH master only [BEO-2780]                                   |        |
| Ultron - Revoke KS                                                                                    |        |
| Ultron - Reminders                                                                                    |        |
| TVPAPItokens [BEO-2911]                                                                               |        |
| Ultron - Change subscription PM [BEO-2766]                                                            |        |
| FinancialReports                                                                                      |        |
| Ultron - Change subscription                                                                          |        |
| Ultron - Support for secure player playback on Phoenix                                                |        |
| Ultron - Gift Cards [BEO-2730]                                                                        |        |
| Vision - Elastic search events notifications                                                          |        |
| Vision - Grant gift - next subscription charges discount                                              |        |
| Vision - Multi-currency support                                                                       |        |
| Vision - Holistic Freemium solution [BEO-3166]                                                        |        |
| Vision - get seasons/episodes APIs [BEO-3234]                                                         |        |
| Vision - Quota Overage [BEO-3163]                                                                     |        |
| Vision - Enable delete household account from TVM                                                     |        |
| Vision - DRM security with DRM ID                                                                     |        |
| Vision - Holdback Mechanism [BEO-3122]                                                                |        |
| Vision - holistic freemium solution - Ads Control as premium service (Updated in Yoda - BEO-BEO-4090] |        |
| Vision - Grace period for auto-deleted recordings [BEO-3214]                                          |        |
| Vision - Cancel purchase event [BEO-3475]                                                             |        |
| Wonder woman - soundex search research                                                                |        |
| Wonder woman - Engagement Notification                                                                |        |
| Wonder woman - Enable app-token to all users [BEO-3172]                                               |        |
| WW - Multiple coupons per subscription [BEO-2643]                                                     |        |
| Wonder Woman - Maxmind enchancements [BEO-3523]                                                       |        |
| WW - Multiple subscription product codes [BEO-3631]                                                   |        |
| Wonder woman - subscription dependencies_groups [BEO-3516]                                            |        |
| Wonder woman - Interests                                                                              |        |
| Wonder woman - Series Reminders                                                                       |        |
| KSQL - exists / not exists operator                                                                   |        |
| WW - HouseholdDevice LoginwithPin [BEO-3787]                                                          |        |
| Wonder Woman - scheduling rules - add rule end-date [BEO-3788]                                        |        |
| Xena - Dependency subscriptionset [BEO-3840]                                                          |        |
| Xena - Package price update from BSS [BEO-3796]                                                       |        |
| Xena - Content de-duplication in a channel list [BEO-3854]                                            |        |
| Xena - KalturaScheduledRecordingProgramFilter [BEO-3890]                                              |        |
| Xena - unified billing cycle [BEO-4022]                                                               |        |
| Yoda - aggregated renewals transaction [BEO-4023]                                                     |        |
| Yoda - Graceful subscription renewal for external wallet[BEO-3940]                                    |        |
| Yoda - Email OTP enablement [BEO-4085]                                                                |        |
| Yoda - Suspend/Resume Household service (Profiles)[BEO-4088]                                          | Tests moved to HouseholdSuspendTests |
| Zatanna - Improving concurrency - group of media assets[BEO-4294]                                     |        |
| Zatanna - Concurrency EPG [BEO - 4255]                                                                |        |
| Zatanna - Collections                                                                                 |        |
| Zatanna - Improving concurrency - updated downgrade handling [BEO-4292]                               |        |
| Zattana - Deleted expired VOD assets from Kaltura DB [BEO-4354]                                       |        |
| Zattana - Nagra SSP integration - New Adapter [BEO-4288]                                              |        |
| Zatanna - Next Package price [BEO-4234]                                                               |        |
| Zatanna - Purchase flow adjustments [BEO-3968]                                                        |        |
| Zatanna - Email for X days before renewal [BEO-4381]                                                  |        |
| Zatanna - Events for end of subscriptions [BEO-4483]                                                  |        |
| Zatanna - GDPR - API to delete HH data [BEO-4482]                                                     |        |
| 4.7.2 - Email notifications control by the user                                                       |        |
| 4.7.2 - SMS notifications                                                                             |        |
| 4_7_2 - Nokia nPVR - Backward compatibility                                                           |        |
| 4.8 - Partial billing for promo (Coupons) [BEO-4763]                                                  |        |
| 4.8 - Purchased content should not be displayed in recommendations [BEO-4764]                         |        |
| 4.8 - Watched content should not be displayed in recommendations [BEO-4823]                           |        |
| 4.8 - Scheduling rule for business model change[BEO-4221]                                             |        |
| 4.8 - Coupons Updates[BEO-4901]                                                                       |        |
| 4.8.2 - GDPR - create scripts that delete the deleted and purged HH[BEO-5010]                         |        |
| 4.8.2 - Multi-Geo Asset Availability Windowing                                                        |        |
| 4.8.2 - Master user can hide part of the linear channel[BEO-4765]                                     |        |