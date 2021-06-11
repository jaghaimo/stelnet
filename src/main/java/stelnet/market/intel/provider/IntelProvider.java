package stelnet.market.intel.provider;

import java.util.Collection;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.market.MarketIntel;
import stelnet.market.filter.FilterManager;
import stelnet.market.intel.IntelList;
import stelnet.market.intel.subject.IntelSubject;

public abstract class IntelProvider {

    public String getDescription() {
        IntelSubject subject = getSubject(null);
        return subject.getIntelTitle();
    }

    public abstract IntelList provide(FilterManager filterManager);

    protected IntelList provide(Collection<MarketAPI> markets) {
        IntelList intels = new IntelList();
        for (MarketAPI market : markets) {
            IntelSubject subject = getSubject(market);
            intels.add(new MarketIntel(market.getFaction(), market.getPrimaryEntity(), subject));
        }
        return intels;
    }

    protected abstract IntelSubject getSubject(MarketAPI market);
}
