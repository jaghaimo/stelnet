
package stelnet.market.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stelnet.filter.submarket.SubmarketFilter;
import stelnet.helper.CollectionHelper;
import stelnet.helper.MarketHelper;
import stelnet.market.IntelList;
import stelnet.market.IntelProvider;
import stelnet.market.filter.FilterManager;

public abstract class SubmarketProvider extends IntelProvider {

    @Override
    public IntelList provide(FilterManager filterManager) {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        SubmarketFilter filter = getFilter();
        CollectionHelper.reduce(submarkets, filter);
        return super.provide(MarketHelper.extractMarkets(submarkets));
    }

    protected abstract SubmarketFilter getFilter();
}
