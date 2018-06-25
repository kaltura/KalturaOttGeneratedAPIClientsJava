asset/action/list with KalturaSearchAssetFilter

Inherited from KalturaFilter
----------------------------
1. OrderBy:
    * VIEWS_DESC
    * NAME_ASC
    * NAME_DESC
    * LIKES_DESC
    * VOTES_DESC
    * RATINGS_DESC
    * START_DATE_DESC
    * START_DATE_ASC
    * RELEVANCY_DESC (edge case)


Inherited from KalturaPersistedFilter
-------------------------------------
* Should be tested in /searchhistory/action/list tests
name - Name for the persisted filter.
If empty, no action will be done. If has value, the filter will be saved and persisted in user's search history.

Inherited from KalturaAssetFilter
---------------------------------
2. dynamicOrderBy
    * order by meta
        * META_ASC (BEO-5254)
        * META_DESC (BEO-5254)

Inherited from KalturaBaseSearchAssetFilter
-------------------------------------------
3. Filter by KSQL - Search assets using various dynamic criteria
* by asset name
* by tag value
* by meta value (TODO)

* By reserved keys
    * media_id
    * epg_id (EPG TODO)
    * epg_channel_id (TODO)
    * asset start date (TODO)
    * asset end date (TODO)
    * asset_type - valid values:
        * media
        * epg
        * recording
    * geo_block - only valid value is "true": When enabled, only assets that are not restricted to the user by geo-block rules will return (TODO)
    * parental_rules - only valid value is "true": When enabled, only assets that the user doesn't need to provide PIN code will return (TODO)
    * user_interests - only valid value is "true". When enabled, only assets that the user defined as his interests (by tags and metas) (TODO)
    * entitled_assets - valid values:
        * free - gets only free to watch assets(TODO)
        * entitled - only those that the user is implicitly entitled to watch (TODO)
        * "both"

* Logical conjunction
    * or
    * and

* Comparison operators
    * For numeric fields:
        * = (TODO)
        * > (TODO)
        * < (TODO)
        * >= (edge case)
        * <= (edge case)
        * : (in)(edge case)

    * For alpha numeric fields:
        * Any word starts with(^)
        * phrase starts with(^=)(edge case)
        * not (!=)
        * like (~)
        * Not like (!~)(edge case)
        * Exists (+)(edge case)
        * Not exists (!+)(edge case)


* limitations
    * search values - 20 characters (TODO)
    * entire filter - 2048 characters (Edge case)


KalturaSearchAssetFilter
----------------------------
4. Filter by asset type (typeIn) - DEPRECATED (use KalturaBaseSearchAssetFilter kSql)
    * specific asset type
    * list of assets types (TODO)



