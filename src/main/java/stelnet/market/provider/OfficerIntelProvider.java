package stelnet.market.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.HasOfficer;
import stelnet.filter.market.MarketFilter;
import stelnet.market.IntelSubject;
import stelnet.market.subject.OfficerSubject;

public class OfficerIntelProvider extends MarketProvider {

    private String personality;

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
