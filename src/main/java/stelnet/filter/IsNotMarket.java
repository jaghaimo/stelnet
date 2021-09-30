package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

@Deprecated
public class IsNotMarket implements Filter<SubmarketAPI> {

    private final MarketAPI market;

    public IsNotMarket(MarketAPI market) {
        this.market = market;
    }

    @Override
    public boolean accept(SubmarketAPI object) {
        return object.getMarket() != market;
    }
}
