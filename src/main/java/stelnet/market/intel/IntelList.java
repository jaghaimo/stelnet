package stelnet.market.intel;

import java.util.ArrayList;

import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;

import stelnet.helper.GlobalSectorHelper;
import stelnet.market.MarketQueryIntel;

public class IntelList extends ArrayList<MarketQueryIntel> {

    private static final long serialVersionUID = 1L;

    public void addIntel(boolean isEnabled) {
        if (!isEnabled) {
            return;
        }
        IntelManagerAPI intelManager = GlobalSectorHelper.getIntelManager();
        for (MarketQueryIntel intel : this) {
            intelManager.addIntel(intel, true);
        }
    }

    public int available() {
        int available = 0;
        for (MarketQueryIntel intel : this) {
            available += intel.isEnding() ? 0 : 1;
        }
        return available;
    }

    public void removeIntel() {
        IntelManagerAPI intelManager = GlobalSectorHelper.getIntelManager();
        for (MarketQueryIntel intel : this) {
            intelManager.removeIntel(intel);
        }
    }
}
