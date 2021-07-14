package stelnet.market_old.intel.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.HasAdministrator;
import stelnet.filter.market.MarketFilter;
import stelnet.market_old.intel.subject.AdminSubject;
import stelnet.market_old.intel.subject.IntelSubject;

public class AdminIntelProvider extends MarketProvider {

    @Override
    protected MarketFilter getFilter() {
        return new HasAdministrator();
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new AdminSubject(market);
    }
}
