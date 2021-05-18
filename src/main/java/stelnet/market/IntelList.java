package stelnet.market;

import java.util.ArrayList;

import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;

import stelnet.helper.GlobalSectorHelper;

public class IntelList extends ArrayList<MarketResultIntel> {

    private static final long serialVersionUID = 1L;

    public void addIntel(boolean isEnabled) {
        if (!isEnabled) {
            return;
        }
        IntelManagerAPI intelManager = GlobalSectorHelper.getIntelManager();
        for (MarketResultIntel intel : this) {
            intelManager.addIntel(intel, true);
        }
    }

    public int available() {
        int available = 0;
        for (MarketResultIntel intel : this) {
            available += intel.isEnding() ? 0 : 1;
        }
        return available;
    }

    public void removeIntel() {
        IntelManagerAPI intelManager = GlobalSectorHelper.getIntelManager();
        for (MarketResultIntel intel : this) {
            intelManager.removeIntel(intel);
        }
    }
}
