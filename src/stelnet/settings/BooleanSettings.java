package stelnet.settings;

import lombok.RequiredArgsConstructor;
import lunalib.lunaSettings.LunaSettings;
import stelnet.util.ModConstants;

@RequiredArgsConstructor
public enum BooleanSettings {
    /**
     * Plugins.
     */
    COMMODITIES("stelnet_General_Basic_Commodities"),
    CONTACTS("stelnet_General_Basic_Contacts"),
    EXPLORATION("stelnet_General_Basic_Exploration"),
    MARKET("stelnet_General_Basic_Market"),
    STORAGE("stelnet_General_Basic_Storage"),

    /**
     * Advanced options.
     */
    AUTO_REFRESH_MARKETS("stelnet_General_Advanced_AutoRefresh"),
    SEBESTYEN("stelnet_General_Advanced_Sebestyen"),
    UNINSTALL("stelnet_General_Advanced_Uninstall"),

    /**
     * Market module configuration.
     */
    QUERY_USE_OPEN_MARKET("stelnet_Market_Query_UseOpenMarket"),
    QUERY_USE_MILITARY_MARKET("stelnet_Market_Query_UseMilitaryMarket"),
    QUERY_USE_BLACK_MARKET("stelnet_Market_Query_UseBlackMarket"),
    QUERY_USE_CUSTOM_MARKET("stelnet_Market_Query_UseCustomMarket"),
    VIEWER_USE_OPEN_MARKET("stelnet_Market_Viewer_UseOpenMarket"),
    VIEWER_USE_MILITARY_MARKET("stelnet_Market_Viewer_UseMilitaryMarket"),
    VIEWER_USE_BLACK_MARKET("stelnet_Market_Viewer_UseBlackMarket"),
    VIEWER_USE_CUSTOM_MARKET("stelnet_Market_Viewer_UseCustomMarket");

    private final String key;

    public boolean get() {
        return LunaSettings.getBoolean(ModConstants.STELNET, key);
    }
}
