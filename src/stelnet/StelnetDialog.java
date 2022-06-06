package stelnet;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignUIAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StelnetDialog implements EveryFrameScript {

    private float advanced = 0;
    private final String message;

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return true;
    }

    @Override
    public void advance(float amount) {
        CampaignUIAPI campaignUi = Global.getSector().getCampaignUI();
        if (campaignUi == null) {
            unregister();
            return;
        }
        advanced += amount;
        if (advanced > 1) {
            Global.getSector().setPaused(true);
            campaignUi.showMessageDialog(message);
            unregister();
        }
    }

    public void register() {
        Global.getSector().getTransientScripts().add(this);
    }

    public void unregister() {
        Global.getSector().getTransientScripts().remove(this);
    }
}
