package stelnet.market.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.HasAdministrator;
import stelnet.filter.market.MarketFilter;
import stelnet.market.IntelSubject;
import stelnet.market.subject.AdminSubject;

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
