package stelnet.market_old.intel.provider;

import java.util.Collection;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.market_old.MarketQueryIntel;
import stelnet.market_old.filter.FilterManager;
import stelnet.market_old.intel.IntelList;
import stelnet.market_old.intel.subject.IntelSubject;

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
            intels.add(new MarketQueryIntel(market.getFaction(), market.getPrimaryEntity(), subject));
        }
        return intels;
    }

    protected abstract IntelSubject getSubject(MarketAPI market);
}
