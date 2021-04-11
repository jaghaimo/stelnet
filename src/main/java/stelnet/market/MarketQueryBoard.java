package stelnet.market;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

import stelnet.helper.IntelHelper;

public class MarketQueryBoard extends BaseIntelPlugin {

    public static MarketQueryBoard getInstance() {
        IntelInfoPlugin intel = IntelHelper.getFirstIntel(MarketResultBoard.class);
        if (intel == null) {
            MarketResultBoard board = new MarketResultBoard();
            IntelHelper.addIntel(board);
        }
        return (MarketQueryBoard) intel;
    }
}
