package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.helper.LogHelper;

public class IsNotHidden implements MarketFilter {

    public boolean accept(MarketAPI market) {
        LogHelper.debug(String.format("Considering %s (%b)", market.getName(), !market.isHidden()));
        return !market.isHidden();
    }
}
