package stelnet.util;

import lunalib.lunaSettings.LunaSettings;

public class ModSettings {

    /**
     * Plugins.
     */
    public static final String COMMODITIES = "stelnet_General_Basic_Commodities";
    public static final String CONTACTS = "stelnet_General_Basic_Contacts";
    public static final String EXPLORATION = "stelnet_General_Basic_Exploration";
    public static final String MARKET = "stelnet_General_Basic_Market";
    public static final String STORAGE = "stelnet_General_Basic_Storage";

    /**
     * Advanced options.
     */
    public static final String AUTO_REFRESH_MARKETS = "stelnet_General_Advanced_AutoRefresh";
    public static final String SEBESTYEN = "stelnet_General_Advanced_Sebestyen";
    public static final String UNINSTALL = "stelnet_General_Advanced_Uninstall";

    /**
     * Market module configuration.
     */
    public static final String QUERY_USE_OPEN_MARKET = "stelnet_Market_Query_UseOpenMarket";
    public static final String QUERY_USE_MILITARY_MARKET = "stelnet_Market_Query_UseMilitaryMarket";
    public static final String QUERY_USE_BLACK_MARKET = "stelnet_Market_Query_UseBlackMarket";
    public static final String QUERY_USE_CUSTOM_MARKET = "stelnet_Market_Query_UseCustomMarket";
    public static final String VIEWER_USE_OPEN_MARKET = "stelnet_Market_Viewer_UseOpenMarket";
    public static final String VIEWER_USE_MILITARY_MARKET = "stelnet_Market_Viewer_UseMilitaryMarket";
    public static final String VIEWER_USE_BLACK_MARKET = "stelnet_Market_Viewer_UseBlackMarket";
    public static final String VIEWER_USE_CUSTOM_MARKET = "stelnet_Market_Viewer_UseCustomMarket";

    public static boolean get(String key) {
        return LunaSettings.getBoolean(ModConstants.STELNET, key);
    }

    public static boolean has(String key) {
        return get(key);
    }

    public static boolean is(String key) {
        return get(key);
    }

    public static boolean use(String key) {
        return get(key);
    }
}
