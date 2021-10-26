package stelnet.filter.market;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class IsNotInSystem implements MarketFilter {

    private final String systemId;

    public IsNotInSystem(String systemId) {
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
