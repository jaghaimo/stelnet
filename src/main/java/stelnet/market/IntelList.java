package stelnet.market;

import java.util.ArrayList;

import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;

import stelnet.helper.GlobalHelper;

public class IntelList extends ArrayList<MarketResultIntel> {

    private static final long serialVersionUID = 1L;

    public void addIntel(boolean isEnabled) {
        if (!isEnabled) {
            return;
        }
        IntelManagerAPI intelManager = GlobalHelper.getIntelManager();
        for (MarketResultIntel intel : this) {
            intelManager.addIntel(intel, true);
        }
    }

    public void removeIntel() {
        IntelManagerAPI intelManager = GlobalHelper.getIntelManager();
        for (MarketResultIntel intel : this) {
            intelManager.removeIntel(intel);
        }
    }
}
