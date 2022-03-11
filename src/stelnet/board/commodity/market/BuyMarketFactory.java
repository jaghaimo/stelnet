package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.filter.MarketCommodityAvailable;
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
        CollectionUtils.reduce(markets, new MarketCommodityAvailable(commodityId));
    }

    @Override
    protected void sortMarkets(List<MarketAPI> markets) {
        super.sortMarkets(markets);
        Collections.reverse(markets);
    }
}
