package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignUIAPI;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;
import stelnet.util.ConfigConstants;
import stelnet.util.ConfigUtils;
import stelnet.util.ReportUtils;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void afterGameSave() {
        if (ConfigConstants.UNINSTALL_MOD) {
            showUninstalledDialog();
        }
    }

    @Override
    public void beforeGameSave() {
        if (ConfigConstants.UNINSTALL_MOD) {
            ConfigUtils.deactivate();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        ReportUtils.generate();
        ConfigUtils.configure();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        if (TutorialMissionIntel.isTutorialInProgress()) {
            ConfigUtils.deactivate();
            return;
        }
        ConfigUtils.activate();
    }

    private void showUninstalledDialog() {
        CampaignUIAPI campaignUi = Global.getSector().getCampaignUI();
        if (campaignUi == null) {
            return;
        }
        campaignUi.showMessageDialog(
            "Stellar Networks has been removed from this save. You can now update (or disable) this mod."
        );
    }
}
