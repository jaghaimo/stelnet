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
    REQUIRE_CIR("stelnet_General_Advanced_RequireCIR"),
    UNINSTALL("stelnet_General_Advanced_Uninstall"),

    /**
     * Contacts module configuration.
     */
    CONTACTS_MISSIONLESS("stelnet_Contacts_Missionless"),
    CONTACTS_SEBESTYEN("stelnet_Contacts_Sebestyen"),

    /**
     * Market module configuration.
     */
    MARKET_USE_OPEN_MARKET("stelnet_Market_UseOpenMarket"),
    MARKET_USE_MILITARY_MARKET("stelnet_Market_UseMilitaryMarket"),
    MARKET_USE_BLACK_MARKET("stelnet_Market_UseBlackMarket"),
    MARKET_USE_CUSTOM_MARKET("stelnet_Market_UseCustomMarket"),
    MARKET_AUTO_REFRESH("stelnet_Market_AdvancedAutoRefresh"),
    MARKET_CODEX_ITEMS("stelnet_Market_AdvancedCodexItems"),
    MARKET_CODEX_SHIPS("stelnet_Market_AdvancedCodexShips");

    private final String key;

    public Boolean get() {
        return LunaSettings.getBoolean(ModConstants.STELNET_ID, key);
    }
}
