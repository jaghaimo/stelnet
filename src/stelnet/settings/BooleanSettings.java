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
    MARKET_USE_OPEN_MARKET("stelnet_Market_UseOpenMarket"),
    MARKET_USE_MILITARY_MARKET("stelnet_Market_UseMilitaryMarket"),
    MARKET_USE_BLACK_MARKET("stelnet_Market_UseBlackMarket"),
    MARKET_USE_CUSTOM_MARKET("stelnet_Market_UseCustomMarket");

    private final String key;

    public boolean get() {
        return LunaSettings.getBoolean(ModConstants.STELNET, key);
    }
}
