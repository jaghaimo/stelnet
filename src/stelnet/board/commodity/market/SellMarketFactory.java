package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import lombok.Getter;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.filter.MarketCommodityDemand;
import stelnet.util.CollectionUtils;

public class SellMarketFactory extends MarketFactory {

    @Getter
    private final DemandPrice price;

    public SellMarketFactory(final String commodityId) {
        super(commodityId);
        this.price = new DemandPrice(commodityId);
    }

    @Override
    protected void filterMarkets(final List<MarketAPI> markets) {
        CollectionUtils.reduce(markets, new MarketCommodityDemand(commodityId));
    }
}
