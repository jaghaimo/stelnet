package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;
import stelnet.util.ConfigConstants;
import stelnet.util.ConfigUtils;
import stelnet.util.ReportUtils;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void beforeGameSave() {
        if (ConfigConstants.UNINSTALL_MOD) {
            ConfigUtils.deactivate();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        ReportUtils.generate();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        if (TutorialMissionIntel.isTutorialInProgress()) {
            ConfigUtils.deactivate();
            return;
        }
        ConfigUtils.configure();
        ConfigUtils.activate();
    }
}
