package stelnet;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignUIAPI;
import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.impl.campaign.tutorial.TutorialMissionIntel;
import java.awt.event.KeyEvent;
import lunalib.lunaSettings.LunaSettings;
import stelnet.settings.BooleanSettings;
import stelnet.util.ModConstants;
import stelnet.util.Reporter;
import stelnet.util.StelnetHelper;

public class StelnetMod extends BaseModPlugin {

    @Override
    public void afterGameSave() {
        if (BooleanSettings.UNINSTALL.get()) {
            DelayedDialog dialog = new DelayedDialog(
                "Stellar Networks has been removed from this save. You can now update (or disable) this mod.",
                1
            );
            dialog.register();
        }
    }

    @Override
    public void beforeGameSave() {
        SettingsListener.resetCache();
        if (BooleanSettings.UNINSTALL.get()) {
            resetIntelUi();
        }
    }

    @Override
    public void onApplicationLoad() throws Exception {
        Reporter.generate();
        SettingsListener.register();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        LunaSettings.reportSettingsChanged(ModConstants.STELNET_ID);
    }

    @Override
    public void onDevModeF8Reload() {
        LunaSettings.reportSettingsChanged(ModConstants.STELNET_ID);
    }

    private void resetIntelUi() {
        if (TutorialMissionIntel.isTutorialInProgress()) {
            return;
        }
        CampaignUIAPI campaignUi = Global.getSector().getCampaignUI();
        if (campaignUi == null) {
            return;
        }
        campaignUi.showCoreUITab(CoreUITabId.INTEL, null);
        StelnetHelper.sendKey(KeyEvent.VK_ESCAPE);
    }
}
