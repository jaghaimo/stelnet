package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public abstract class MarketFilter extends Filter {

    @Override
    public abstract boolean accept(MarketAPI market);
}
