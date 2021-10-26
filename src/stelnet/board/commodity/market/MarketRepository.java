package stelnet.board.commodity.market;

import java.util.List;
import lombok.Getter;
import stelnet.board.commodity.CommodityTab;

@Getter
public class MarketRepository {

    List<MarketApiWrapper> buyMarkets;
    List<MarketApiWrapper> sellMarkets;

    public MarketRepository(String commodityId) {
        buyMarkets = new BuyMarketFactory(commodityId).createMarkets();
        sellMarkets = new SellMarketFactory(commodityId).createMarkets();
    }

    public List<MarketApiWrapper> getMarketsByType(CommodityTab marketType) {
        if (marketType == CommodityTab.SELL) {
            return sellMarkets;
        }
        return buyMarkets;
    }
}
