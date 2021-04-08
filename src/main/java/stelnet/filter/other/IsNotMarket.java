package stelnet.filter.other;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stelnet.filter.Filter;

public class IsNotMarket implements Filter<SubmarketAPI> {

    private MarketAPI market;

    public IsNotMarket(MarketAPI market) {
        this.market = market;
    }

    @Override
    public boolean accept(SubmarketAPI object) {
        return object.getMarket() != market;
    }
}
