package stelnet.settings;

import lombok.RequiredArgsConstructor;
import lunalib.lunaSettings.LunaSettings;
import stelnet.util.ModConstants;

@RequiredArgsConstructor
public enum CaptainsLogSettings {
    COLONY_STRUCTURES("CaptainsLog_Enable_Colony_Structures"),
    COMM_RELAYS("CaptainsLog_Enable_Comm_Relays"),
    RUINS("CaptainsLog_Enable_Ruins"),
    SALVAGEABLE("CaptainsLog_Enable_Salvageable");

    private final String key;

    public Boolean isEnabled() {
        Boolean setting = LunaSettings.getBoolean(ModConstants.CAPTAINS_LOG_ID, key);
        if (setting == null) {
            return false;
        }
        return setting;
    }
}
