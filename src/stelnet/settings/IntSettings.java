package stelnet.settings;

import lombok.RequiredArgsConstructor;
import lunalib.lunaSettings.LunaSettings;
import stelnet.util.ModConstants;

@RequiredArgsConstructor
public enum IntSettings {
    /**
     * Commodity profit tab.
     */
    COMMODITY_PROFIT_ROW_NUMBER("stelnet_Commodity_Profit_ResultNumber"),
    COMMODITY_PROFIT_MIN_QUANTITY("stelnet_Commodity_Profit_MinQuantity"),
    COMMODITY_PROFIT_MIN_PROFIT("stelnet_Commodity_Profit_MinProfit");

    private final String key;

    public int get() {
        return LunaSettings.getInt(ModConstants.STELNET, key);
    }
}
