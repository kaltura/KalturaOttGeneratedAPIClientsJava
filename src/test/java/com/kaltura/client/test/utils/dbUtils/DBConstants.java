package com.kaltura.client.test.utils.dbUtils;

import com.kaltura.client.test.tests.enums.ChannelType;

public class DBConstants {

    // fields:
    static final String ACTIVATION_TOKEN = "activation_token";
    static final String CODE = "code";
    static final String CHANNEL_TYPE = "channel_type";
    static final String CP_TOKEN = "cp_token";
    static final String FILTER_EXPRESSION = "ksql_filter";
    static final String FULL_LIFE_CYCLE_MINUTES = "full_life_cycle_min";
    static final String ID = "id";
    static final String INT_DISCOUNT_ID = "internal_discount_id";
    static final String IS_ACTIVATION_NEEDED = "is_activation_needed";
    static final String IS_RENEWED = "is_renew";
    static final String MAX_VIEWS_COUNT = "max_views_number";
    static final String NAME = "name";
    static final String NUMBER_OF_REC_PERIODS = "num_of_rec_periods";
    static final String PASSWORD = "password";
    static final String PRICE_PLAN_ID = "usage_module_code";
    static final String PRICING_ID = "pricing_id";
    static final String ROW_COUNT = "row_count";
    static final String SERV_ID = "serv_id";
    static final String SUBSCRIPTION_ONLY = "subscription_only";
    static final String SUB_ID = "sub_id";
    static final String TAG_TYPE = "tag_type";
    static final String TAG_VALUE = "tag_value";
    static final String USERNAME = "username";
    static final String VIEW_LIFE_CYCLE_MINUTES = "view_life_cycle_min";

    //queries
    static final String ACTIVATION_TOKEN_SELECT = "SELECT [ACTIVATION_TOKEN] FROM [Users].[dbo].[users] WHERE [USERNAME] = ?";

    static final String AND_ACTIVE_STATUS = " and u.activate_status=1";

    static final String RESET_PASSWORD_TOKEN_SELECT = "SELECT [CP_TOKEN] FROM [Users].[dbo].[users] WHERE [USERNAME] = ?";

    static final String CHANNEL_EXPRESSION_SELECT = "select t.tag_type_id as tag_name, t.value as tag_value\n" +
            "from [TVinci].[dbo].[tags] t, [TVinci].[dbo].[channel_tags] ct\n" +
            "where ct.status=1 and t.status=1 and ct.channel_id=? and ct.tag_id=t.id";

    static final String CHANNEL_SELECT = "select *\n" +
            "from [TVinci].[dbo].[channels]\n" +
            "where id=?";

    static final String CHECK_IS_ACTIVATION_USERS_NEEDED = "select [IS_ACTIVATION_NEEDED]\n" +
            "from [Users].[dbo].[groups_parameters]\n" +
            "where group_id= ?";

    static final String CURRENCY_CODE_SELECT = "select ID from [Pricing].[dbo].[lu_currency] WHERE CODE3=?";

    static final String DISCOUNT_BY_PERCENT = "select TOP (1) *\n" +
            "from [Pricing].[dbo].[discount_codes] dc \n" +
            "where dc.discount_percent=?\n" + // percent amount
            "and dc.group_id=?\n" + // group
            "and dc.[status]=1 and dc.is_active=1";

    static final String DISCOUNT_BY_PERCENT_AND_CURRENCY = "select TOP (1) *\n" +
            "from [Pricing].[dbo].[discount_codes] dc with(nolock)\n" +
            "join [Pricing].[dbo].[lu_currency] lc with(nolock) on (dc.currency_cd=lc.id)\n" +
            "where lc.code3=?\n" + // CURRENCY
            "and dc.discount_percent=?\n" + // percent amount
            "and dc.group_id=?\n" + // group
            "and dc.[status]=1 and dc.is_active=1";

    static final String DISCOUNT_BY_PRICE_AND_PERCENT_SELECT = "select TOP (1) *\n" +
            "from [Pricing].[dbo].[discount_codes]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=?\n" + // group
            "and price=?\n" + // price amount
            "and discount_percent=?";  // percent amount

    static final String EPG_CHANNEL_ID_SELECT = "SELECT [ID] FROM [TVinci].[dbo].[epg_channels] WHERE [GROUP_ID] = ? AND [NAME] = ?";

    static final String INGEST_ITEMS_DATA_SELECT = "select TOP (1) *\n" +
            "from [Tvinci].[dbo].[groups_passwords]\n" +
            "where [group_id]=? and is_active=1 and [status]=1 order by UPDATE_DATE DESC";

    static final String PPV_SELECT_BY_PRICE_PLAN = "select top 1 * from [Pricing].[dbo].[ppv_modules]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=? and usage_module_code=?\n" +
            "order by create_date";

    static final String PRICE_CODE_SELECT = "select top 1 * from [Pricing].[dbo].[price_codes] pc\n" +
            "join [Pricing].[dbo].[lu_currency] lc with(nolock) on (pc.currency_cd=lc.id)\n" +
            "where pc.[status]=1 and pc.is_active=1\n" +
            "and pc.group_id=? and pc.price=? and lc.CODE3=?";

    static final String PRICE_PLAN_SELECT = "select top 1 * from [Pricing].[dbo].[usage_modules]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=? and internal_discount_id=? and pricing_id=?";

    static final String PRICE_PLAN_WITH_WAVER_SELECT = "SELECT TOP 1 * FROM [Pricing].[dbo].[usage_modules]\n" +
            "WHERE group_id=? AND WAIVER=1 AND WAIVER_PERIOD>0 AND IS_ACTIVE=1 AND [STATUS]=1";

    static final String PRICE_PLAN_WITHOUT_WAVER_SELECT = "SELECT TOP 1 * FROM [Pricing].[dbo].[usage_modules]\n" +
            "WHERE group_id=? AND WAIVER=0 AND IS_ACTIVE=1 AND [STATUS]=1 AND EXT_DISCOUNT_ID IS NOT NULL AND INTERNAL_DISCOUNT_ID IS NOT NULL";

    static final String PRICE_PLAN_5_MIN_RENEW_SELECT = "select top 1 * from [Pricing].[dbo].[usage_modules]\n" +
            "where [status]=1 and is_active=1\n" +
            "and view_life_cycle_min = 5\n" +
            "and full_life_cycle_min = 5\n" +
            "and is_renew=1\n" +
            "and ((num_of_rec_periods > 2) or (num_of_rec_periods = 0))\n" + // TODO: do we really want it "num_of_rec_periods > 2"?
            "and group_id=?";

    static final String SUBSCRIPTION_SELECT = "select top 1 * from [Pricing].[dbo].[subscriptions] s with(nolock)\n" +
            "join [Pricing].[dbo].[subscriptions_channels] sc with(nolock) on (sc.subscription_id=s.id)\n" +
            "join [TVinci].[dbo].[channels] c with(nolock) on (c.id=sc.channel_id)\n" +
            "where s.[status]=1 and s.is_active=1\n" +
            "and s.group_id=? and s.usage_module_code=? and s.discount_module_code=? and c.channel_type!=" +
            ChannelType.MANUAL_CHANNEL_TYPE.getValue() + " and sc.is_active=1 and sc.[status]=1\n" +
            "order by s.create_date";

    static final String SUBSCRIPTION_5_MIN_RENEW_SELECT = "select top 1 * from [Pricing].[dbo].[subscriptions] s with(nolock)\n" +
            "join [Pricing].[dbo].[usage_modules] um with(nolock) on (s.usage_module_code=um.id)\n" +
            "join [Pricing].[dbo].[subscriptions_channels] sc with(nolock) on (sc.subscription_id=s.id)\n" +
            "join [TVinci].[dbo].[channels] c with(nolock) on (c.id=sc.channel_id)\n" +
            "where um.[status]=1 and um.is_active=1 and um.view_life_cycle_min=5 and um.full_life_cycle_min=5 and " +
            // TODO: not sure about um.num_of_rec_periods > 2
            "um.is_renew=1 and ((um.num_of_rec_periods > 2) or (um.num_of_rec_periods = 0))\n" +
            "and s.is_recurring=1 and c.channel_type!=" + ChannelType.MANUAL_CHANNEL_TYPE.getValue() + " " +
            "and s.group_id=? and s.is_active=1 and s.[status]=1 and s.[type]=0 and sc.is_active=1 and sc.[status]=1\n" +
            "order by s.create_date desc";

    static final String SUBSCRIPTION_WITH_PREMIUM_SERVICE_SELECT = "select TOP (1) SUBSCRIPTION_ID as " + SUB_ID +
            ", SERVICE_ID as " + SERV_ID + "\n" +
            "FROM [Pricing].[dbo].[subscriptions] s\n" +
            "INNER JOIN [Pricing].[dbo].[subscriptions_services] ss\n" +
            "ON s.ID = SS.SUBSCRIPTION_ID\n" +
            "where s.group_id=?";

    static final String COLLECTION_SELECT = "select top 1 * from [Pricing].[dbo].[collections]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=? and discount_id=? and price_id=? and usage_module_id=?\n" +
            "order by create_date";

    static final String USER_BY_ROLE_SELECT = "select top(1) u.username, u.[password]\n" +
            "from [Users].[dbo].[users] u with(nolock)\n" +
            "join [Users].[dbo].[users_roles] ur with(nolock) on (u.id=ur.[user_id])\n" +
            "join [TVinci].[dbo].[roles] r with(nolock) on (r.id=ur.role_id)\n" +
            // TODO: find instead of and u.username <> 'lfaingold' how to exclude suspended users
            "where r.[NAME]=? and u.is_active=1 and u.[status]=1 and u.group_id=? and u.username <> 'lfaingold'";

    static final String COUNT_RECORDS_BY_ROLE_NAME_IN_ROLES_SELECT = "select count(*) as " + ROW_COUNT + "\n" +
            "from [TVinci].[dbo].[roles]\n" +
            "where [NAME]=? and is_active=1 and [status]=1 and group_id=?";

    static final String ID_BY_ROLE_NAME_IN_ROLES_SELECT = "select " + ID + "\n" +
            "from [TVinci].[dbo].[roles]\n" +
            "where [NAME]=? and is_active=1 and [status]=1 and group_id=?";

    static final String COUNT_RECORDS_BY_ROLE_NAME_IN_PERMISSIONS_SELECT = "select count(*) as " + ROW_COUNT + "\n" +
            "from [TVinci].[dbo].[permissions]\n" +
            "where [NAME]=? and is_active=1 and [status]=1 and group_id=?";

    static final String ID_BY_ROLE_NAME_IN_PERMISSIONS_SELECT = "select " + ID + "\n" +
            "from [TVinci].[dbo].[permissions]\n" +
            "where [NAME]=? and is_active=1 and [status]=1 and group_id=?";

    static final String COUNT_RECORDS_IN_ROLES_PERMISSIONS_SELECT = "select count(*) as " + ROW_COUNT + "\n" +
            "from [TVinci].[dbo].[roles_permissions]\n" +
            "where role_id=? and permission_id=? and is_active=1 and [status]=1 and group_id=?";

    static final String COUNT_RECORDS_BY_NAME_IN_PERMISSION_ITEMS_SELECT = "select count(*) as " + ROW_COUNT + "\n" +
            "from [TVinci].[dbo].[permission_items]\n" +
            "where [NAME]=? and is_active=1 and [status]=1 and group_id=?";

    static final String ID_BY_NAME_IN_PERMISSION_ITEMS_SELECT = "select " + ID + "\n" +
            "from [TVinci].[dbo].[permission_items]\n" +
            "where [NAME]=? and is_active=1 and [status]=1 and group_id=?";

    static final String COUNT_RECORDS_IN_PERMISSIONS_PERMISSIONS_ITEMS_SELECT = "select count(*) as " + ROW_COUNT + "\n" +
            "from [TVinci].[dbo].[permissions_permission_items]\n" +
            "where permission_id=? and permission_item_id=? and is_active=1 and [status]=1 and group_id=?";

    static final String ASSET_ID_SELECT = "SELECT [media_id],[name] FROM [TVinci].[dbo].[epg_channels] WHERE group_id=? and status=1 and DATALENGTH(media_id) > 0";

    static final String UNACTIVE_ASSET_ID_SELECT = "SELECT top 1 [id] FROM [TVinci].[dbo].[media] where group_id = ? and status = 2";

    static final String HOUSEHOLD_BY_ID_SELECT = "SELECT * from [Users].[dbo].[domains] where group_id = ? and ID = ?";

    static final String USER_BY_ID_SELECT = "SELECT * from [Users].[dbo].[users] where group_id = ? and ID = ?";

    static final String ASSETS_SELECT = "SELECT top (?) NAME FROM [TVinci].[dbo].[media] where group_id = ? and status = 1 and is_Active = 1 order by id desc";


    // STORED PROCEDURES:
    static final String SP_INSERT_PERMISSION = "{call TVinci.dbo.__482V0__Insert_Permission(?, ?, ?, ?)}";
    static final String SP_INSERT_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Insert_PermissionItem(?, ?, ?, ?, ?, ?)}";
    static final String SP_INSERT_PERMISSION_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Insert_PermissionPermissionItem(?, ?, ?, ?)}";
    static final String SP_INSERT_PERMISSION_ROLE = "{call TVinci.dbo.__482V0__Insert_PermissionRole(?, ?, ?, ?)}";
    static final String SP_INSERT_ROLE = "{call TVinci.dbo.__482V0__Insert_Role(?, ?)}";
    static final String SP_DELETE_PERMISSION = "{call TVinci.dbo.__482V0__Delete_Permission(?)}";
    static final String SP_DELETE_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Delete_PermissionItem(?)}";
    static final String SP_DELETE_PERMISSION_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Delete_PermissionPermissionItem(?)}";
    static final String SP_DELETE_ROLE_AND_ITS_PERMISSIONS = "{call TVinci.dbo.__482V0__Delete_RolePermission(?, ?)}";


}
