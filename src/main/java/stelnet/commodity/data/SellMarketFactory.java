package stelnet.commodity.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.CommodityDemandFilter;
import stelnet.helper.CollectionHelper;

public class SellMarketFactory extends MarketFactory {

    private DemandPrice price;

    public SellMarketFactory(String commodityId) {
        super(commodityId);
        this.price = new DemandPrice(commodityId);
    }

    @Override
    protected void filterMarkets(List<MarketAPI> markets) {
        CollectionHelper.reduce(markets, new CommodityDemandFilter(commodityId));
    }

    @Override
    protected void sortMarkets(List<MarketAPI> markets) {
        Collections.sort(markets, new Comparator<MarketAPI>() {

            @Override
            public int compare(MarketAPI marketA, MarketAPI marketB) {
                float priceA = getPrice(marketA);
                float priceB = getPrice(marketB);
                return (int) Math.signum(priceB - priceA);
            }
        });
    }

    private float getPrice(MarketAPI market) {
        return price.getPrice(market);
    }
}
