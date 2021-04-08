package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class IsDiscovered implements MarketFilter {

    public boolean accept(MarketAPI market) {
        return !market.getPrimaryEntity().isDiscoverable();
    }
}
