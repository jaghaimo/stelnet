package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;
import stelnet.util.Configurator;
import stelnet.util.ModSettings;
import stelnet.util.Reporter;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void afterGameSave() {
        if (ModSettings.is(ModSettings.UNINSTALL)) {
            DelayedDialog dialog = new DelayedDialog(
                "Stellar Networks has been removed from this save. You can now update (or disable) this mod.",
                1
            );
            dialog.register();
        }
    }

    @Override
    public void beforeGameSave() {
        Configurator.resetCache();
        if (ModSettings.is(ModSettings.UNINSTALL)) {
            Configurator.deactivate(false);
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        Reporter.generate();
        SettingsListener.register();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        Configurator.resetCache();
        if (TutorialMissionIntel.isTutorialInProgress()) {
            Configurator.deactivate(true);
            return;
        }
        Configurator.activate();
    }

    @Override
    public void onDevModeF8Reload() {
        Configurator.deactivate(false);
        Configurator.activate();
    }
}
