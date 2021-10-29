package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;
import stelnet.board.commodity.market.price.SupplyPrice;
import stelnet.filter.MarketCommodityAvailableFilter;
import stelnet.util.CollectionUtils;

public class BuyMarketFactory extends MarketFactory {

    @Getter
    private final SupplyPrice price;

    public BuyMarketFactory(String commodityId) {
        super(commodityId);
        this.price = new SupplyPrice(commodityId);
    }

    @Override
    protected void filterMarkets(List<MarketAPI> markets) {
        CollectionUtils.reduce(markets, new MarketCommodityAvailableFilter(commodityId));
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
                    return (int) Math.signum(priceA - priceB);
                }
            }
        );
    }

    private float getPriceAmount(MarketAPI market) {
        return price.getPriceAmount(market);
    }
}
