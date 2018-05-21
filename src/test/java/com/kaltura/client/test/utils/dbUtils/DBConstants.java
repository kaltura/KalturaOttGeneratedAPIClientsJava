package com.kaltura.client.test.utils.dbUtils;

public class DBConstants {

    // fields:
    public static final String ACTIVATION_TOKEN = "activation_token";
    public static final String CODE = "code";
    public static final String CP_TOKEN = "cp_token";
    public static final String FULL_LIFE_CYCLE_MINUTES = "full_life_cycle_min";
    public static final String ID = "id";
    public static final String IS_ACTIVATION_NEEDED = "is_activation_needed";
    public static final String IS_RENEWED = "is_renew";
    public static final String MAX_VIEWS_COUNT = "max_views_number";
    public static final String NAME = "name";
    public static final String NUMBER_OF_REC_PERIODS = "num_of_rec_periods";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String VIEW_LIFE_CYCLE_MINUTES = "view_life_cycle_min";

    //queries
    public static final String ACTIVATION_TOKEN_SELECT = "SELECT [ACTIVATION_TOKEN] FROM [Users].[dbo].[users] WHERE [USERNAME] = '%S'";

    public static final String AND_ACTIVE_STATUS = " and u.activate_status=1";

    public static final String RESET_PASSWORD_TOKEN_SELECT = "SELECT [CP_TOKEN] FROM [Users].[dbo].[users] WHERE [USERNAME] = '%S'";

    public static final String CHECK_IS_ACTIVATION_USERS_NEEDED = "select [IS_ACTIVATION_NEEDED]\n" +
            "from [Users].[dbo].[groups_parameters]\n" +
            "where group_id=%d";

    public static final String DISCOUNT_BY_PERCENT_AND_CURRENCY = "select TOP (1) *\n" +
            "from [Pricing].[dbo].[discount_codes] dc with(nolock)\n" +
            "join [Pricing].[dbo].[lu_currency] lc with(nolock) on (dc.currency_cd=lc.id)\n" +
            "where lc.code3='%S'\n" + // CURRENCY
            "and dc.discount_percent=%d\n" + // percent amount
            "and dc.group_id=%d\n" + // group
            "and dc.[status]=1 and dc.is_active=1";

    public static final String DISCOUNT_BY_PRICE_AND_PERCENT_SELECT = "select TOP (1) *\n" +
            "from [Pricing].[dbo].[discount_codes]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=%d\n" + // group
            "and price=%f\n" + // price amount
            "and discount_percent=%f";  // percent amount

    public static final String EPG_CHANNEL_ID_SELECT = "SELECT [ID] FROM [TVinci].[dbo].[epg_channels] WHERE [GROUP_ID] = %d AND [NAME] = '%S'";

    public static final String INGEST_ITEMS_DATA_SELECT = "select TOP (1) *\n" +
            "from [Tvinci].[dbo].[groups_passwords]\n" +
            "where [group_id]=%d order by UPDATE_DATE DESC";

    public static final String PRICE_CODE_SELECT = "select top 1 * from [Pricing].[dbo].[price_codes] pc\n" +
            "join [Pricing].[dbo].[lu_currency] lc with(nolock) on (pc.currency_cd=lc.id)\n" +
            "where pc.[status]=1 and pc.is_active=1\n" +
            "and pc.group_id=%d and pc.price=%f and lc.CODE3='%S'";

    public static final String PRICE_PLAN_SELECT = "select top 1 * from [Pricing].[dbo].[usage_modules]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=%d and internal_discount_id=%d and pricing_id=%d";

    public static final String SUBSCRIPTION_SELECT = "select top 1 * from [Pricing].[dbo].[subscriptions]\n" +
            "where [status]=1 and is_active=1\n" +
            "and group_id=%d and usage_module_code=%d\n" +
            "order by create_date";

    public static final String USER_BY_ROLE_SELECT = "select top(1) u.username, u.[password]\n" +
            "from [Users].[dbo].[users] u with(nolock)\n" +
            "join [Users].[dbo].[users_roles] ur with(nolock) on (u.id=ur.[user_id])\n" +
            "join [TVinci].[dbo].[roles] r with(nolock) on (r.id=ur.role_id)\n" +
            "where r.[NAME]='%S' and u.is_active=1 and u.[status]=1 and u.group_id=%d";

    public static final String USER_ROLES_SELECT = "SELECT [ROLE_ID] FROM [Users].[dbo].[users_roles] WHERE [USER_ID] = '%S'";

    // STORED PROCEDURES:
    public static final String SP_INSERT_PERMISSION = "{call TVinci.dbo.__482V0__Insert_Permission(?, ?, ?, ?)}";
    public static final String SP_INSERT_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Insert_PermissionItem(?, ?, ?, ?, ?, ?)}";
    public static final String SP_INSERT_PERMISSION_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Insert_PermissionPermissionItem(?, ?, ?, ?)}";
    public static final String SP_INSERT_PERMISSION_ROLE = "{call TVinci.dbo.__482V0__Insert_PermissionRole(?, ?, ?, ?)}";
    public static final String SP_INSERT_ROLE = "{call TVinci.dbo.__482V0__Insert_Role(?, ?)}";
    public static final String SP_DELETE_PERMISSION = "{call TVinci.dbo.__482V0__Delete_Permission(?)}";
    public static final String SP_DELETE_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Delete_PermissionItem(?)}";
    public static final String SP_DELETE_PERMISSION_PERMISSION_ITEM = "{call TVinci.dbo.__482V0__Delete_PermissionPermissionItem(?)}";
    public static final String SP_DELETE_ROLE_AND_ITS_PERMISSIONS = "{call TVinci.dbo.__482V0__Delete_RolePermission(?, ?)}";
}
