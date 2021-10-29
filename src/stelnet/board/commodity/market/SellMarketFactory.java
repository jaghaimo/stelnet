package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import stelnet.board.commodity.market.price.DemandPrice;
import stelnet.board.commodity.market.price.Price;
import stelnet.filter.MarketCommodityDemandFilter;
import stelnet.util.CollectionUtils;

public class SellMarketFactory extends MarketFactory {

    private final DemandPrice price;

    public SellMarketFactory(String commodityId) {
        super(commodityId);
        this.price = new DemandPrice(commodityId);
    }

    @Override
    protected void filterMarkets(List<MarketAPI> markets) {
        CollectionUtils.reduce(markets, new MarketCommodityDemandFilter(commodityId));
    }

    @Override
    protected void sortMarkets(List<MarketAPI> markets) {
        Collections.sort(
            markets,
            new Comparator<MarketAPI>() {
                @Override
                public int compare(MarketAPI marketA, MarketAPI marketB) {
                    float priceA = getPriceAmount(marketA);
                    float priceB = getPriceAmount(marketB);
                    return (int) Math.signum(priceB - priceA);
                }
            }
        );
    }

    @Override
    protected Price getPrice() {
        return this.price;
    }

    private float getPriceAmount(MarketAPI market) {
        return price.getPriceAmount(market);
    }
}
