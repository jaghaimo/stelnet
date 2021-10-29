package stelnet.filter;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketIsNotInSystem extends MarketFilter {

    private final String systemId;

    public MarketIsNotInSystem(String systemId) {
        this.systemId = systemId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        StarSystemAPI starSystem = market.getStarSystem();
        if (starSystem == null) {
            return true;
        }
        return !starSystem.getId().equalsIgnoreCase(systemId);
    }
}
