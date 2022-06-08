package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;
import stelnet.util.ConfigConstants;
import stelnet.util.Configurator;
import stelnet.util.Reporter;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void afterGameSave() {
        if (ConfigConstants.UNINSTALL_MOD) {
            StelnetDialog dialog = new StelnetDialog(
                "Stellar Networks has been removed from this save. You can now update (or disable) this mod.",
                1
            );
            dialog.register();
        }
    }

    @Override
    public void beforeGameSave() {
        if (ConfigConstants.UNINSTALL_MOD) {
            Configurator.deactivate(false);
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        Reporter.generate();
        Configurator.configure();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        if (TutorialMissionIntel.isTutorialInProgress()) {
            Configurator.deactivate(true);
            return;
        }
        Configurator.activate();
    }
}
