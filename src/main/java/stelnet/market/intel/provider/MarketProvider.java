package stelnet.market.intel.provider;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.MarketFilter;
import stelnet.helper.CollectionHelper;
import stelnet.helper.MarketHelper;
import stelnet.market.filter.FilterManager;
import stelnet.market.intel.IntelList;

public abstract class MarketProvider extends IntelProvider {

    @Override
    public IntelList provide(FilterManager filterManager) {
        List<MarketAPI> markets = MarketHelper.getMarkets();
        MarketFilter filter = getFilter();
        CollectionHelper.reduce(markets, filter);
        return super.provide(markets);
    }

    protected abstract MarketFilter getFilter();
}
