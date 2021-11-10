package stelnet.board.query;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.BaseIntel;
import stelnet.IntelInfo;

public class IntelTemplate extends BaseIntel {

    public IntelTemplate(MarketAPI market) {
        super(market.getFaction(), market.getPrimaryEntity());
    }

    @Override
    protected IntelInfo getIntelInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String getTag() {
        // TODO Auto-generated method stub
        return null;
    }
}
