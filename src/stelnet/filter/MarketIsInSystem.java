package stelnet.filter;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarketIsInSystem extends MarketFilter {

    private final String systemId;

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        StarSystemAPI starSystem = market.getStarSystem();
        if (starSystem == null) {
            return false;
        }
        return systemId.equalsIgnoreCase(starSystem.getId());
    }
}
