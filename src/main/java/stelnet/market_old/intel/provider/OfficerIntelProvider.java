package stelnet.market_old.intel.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.HasOfficer;
import stelnet.filter.market.MarketFilter;
import stelnet.market_old.intel.subject.IntelSubject;
import stelnet.market_old.intel.subject.OfficerSubject;

public class OfficerIntelProvider extends MarketProvider {

    private final String personality;

    public OfficerIntelProvider(String p) {
        personality = p;
    }

    @Override
    protected MarketFilter getFilter() {
        return new HasOfficer(personality);
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new OfficerSubject(personality, market);
    }
}
