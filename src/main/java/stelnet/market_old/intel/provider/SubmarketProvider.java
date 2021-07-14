
package stelnet.market_old.intel.provider;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stelnet.filter.submarket.IsNotStorage;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.helper.CollectionHelper;
import stelnet.helper.MarketHelper;
import stelnet.market_old.filter.FilterManager;
import stelnet.market_old.intel.IntelList;

public abstract class SubmarketProvider extends IntelProvider {

    @Override
    public IntelList provide(FilterManager filterManager) {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        List<SubmarketFilter> filters = new LinkedList<>();
        filters.add(getFilter());
        filters.add(new IsNotStorage());
        CollectionHelper.reduce(submarkets, filters);
        return super.provide(MarketHelper.extractMarkets(submarkets));
    }

    protected abstract SubmarketFilter getFilter();
}
