package stelnet;

import com.fs.starfarer.api.GameState;
import com.fs.starfarer.api.Global;
import lunalib.lunaSettings.LunaSettings;
import lunalib.lunaSettings.LunaSettingsListener;
import stelnet.util.Configurator;
import stelnet.util.ModConstants;
import stelnet.util.ModSettings;

public class SettingsListener implements LunaSettingsListener {

    public static void register() {
        LunaSettings.addSettingsListener(new SettingsListener());
    }

    @Override
    public void settingsChanged(String modId) {
        if (!ModConstants.STELNET.equals(modId)) {
            return;
        }
        if (Global.getCurrentState().equals(GameState.TITLE)) {
            return;
        }
        if (ModSettings.is(ModSettings.UNINSTALL)) {
            Configurator.deactivate(false);
        } else {
            Configurator.activate();
        }
    }
}
