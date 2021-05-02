package stelnet.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.commodity.market.price.DemandPrice;
import stelnet.commodity.market.price.Price;
import stelnet.filter.market.CommodityDemandFilter;
import stelnet.helper.CollectionHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SellMarketFactory extends MarketFactory {

    private final DemandPrice price;

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
                float priceA = getPriceAmount(marketA);
                float priceB = getPriceAmount(marketB);
                return (int) Math.signum(priceB - priceA);
            }
        });
    }

    @Override
    protected Price getPrice() {
        return this.price;
    }

    private float getPriceAmount(MarketAPI market) {
        return price.getPriceAmount(market);
    }
}
