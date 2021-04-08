package stelnet.commodity.extractor;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.MarketNotHidden;
import stelnet.helper.CollectionHelper;
import stelnet.helper.GlobalHelper;

public abstract class MarketFactory {

    protected String commodityId;

    public MarketFactory(String commodityId) {
        this.commodityId = commodityId;
    }

    public List<MarketAPI> getMarkets() {
        EconomyAPI economy = GlobalHelper.getEconomy();
        List<MarketAPI> markets = economy.getMarketsCopy();
        CollectionHelper.reduce(markets, new MarketNotHidden());
        filterMarkets(markets);
        sortMarkets(markets);
        return markets;
    }

    protected abstract void filterMarkets(List<MarketAPI> markets);

    protected abstract void sortMarkets(List<MarketAPI> markets);
}
