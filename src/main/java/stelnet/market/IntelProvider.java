package stelnet.market;

import java.util.Collection;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.market.filter.FilterManager;

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
            intels.add(new MarketResultIntel(market.getFaction(), market.getPrimaryEntity(), subject));
        }
        return intels;
    }

    protected abstract IntelSubject getSubject(MarketAPI market);
}
