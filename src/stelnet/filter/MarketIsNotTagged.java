package stelnet.filter;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketIsNotTagged extends MarketFilter {

    private final String tag;

    public MarketIsNotTagged(String tag) {
        this.tag = tag;
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        if (market.hasTag(tag)) {
            return false;
        }

        StarSystemAPI starSystem = market.getStarSystem();
        if (starSystem == null) {
            return true;
        }

        return !starSystem.hasTag(tag);
    }
}
