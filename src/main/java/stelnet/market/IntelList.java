package stelnet.market;

import java.util.ArrayList;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;

public class IntelList extends ArrayList<MarketResultIntel> {

    private static final long serialVersionUID = 1L;

    public void addIntel(boolean isEnabled) {
        if (!isEnabled) {
            return;
        }
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        for (MarketResultIntel intel : this) {
            intelManager.addIntel(intel, true);
        }
    }

    public void removeIntel() {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        for (MarketResultIntel intel : this) {
            intelManager.removeIntel(intel);
        }
    }
}
