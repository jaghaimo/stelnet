package stelnet.commodity.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.CommodityAvailableFilter;
import stelnet.helper.CollectionHelper;

public class BuyMarketFactory extends MarketFactory {

    private final SupplyPrice price;

    public BuyMarketFactory(String commodityId) {
        super(commodityId);
        this.price = new SupplyPrice(commodityId);
    }

    @Override
    protected void filterMarkets(List<MarketAPI> markets) {
        CollectionHelper.reduce(markets, new CommodityAvailableFilter(commodityId));
    }

    @Override
    protected void sortMarkets(List<MarketAPI> markets) {
        Collections.sort(markets, new Comparator<MarketAPI>() {

            @Override
            public int compare(MarketAPI marketA, MarketAPI marketB) {
                float priceA = getPrice(marketA);
                float priceB = getPrice(marketB);
                return (int) Math.signum(priceA - priceB);
            }
        });
    }

    private float getPrice(MarketAPI market) {
        return price.getPrice(market);
    }
}
