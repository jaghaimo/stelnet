package stelnet.commodity.market;

import java.util.HashMap;
import java.util.List;

import stelnet.commodity.CommodityTab;

public class MarketRepository {

    HashMap<String, List<MarketApiWrapper>> buyMarkets = new HashMap<>();
    HashMap<String, List<MarketApiWrapper>> sellMarkets = new HashMap<>();
    // TODO investigate making this non-static
    private static MarketRepository instance;

    public MarketRepository(String commodityId) {
        List<MarketApiWrapper> buyMarket = new BuyMarketFactory(commodityId).createMarkets();
        List<MarketApiWrapper> sellMarket = new SellMarketFactory(commodityId).createMarkets();

        buyMarkets.put(commodityId, buyMarket);
        sellMarkets.put(commodityId, sellMarket);
        instance = this;
    }

    public static MarketRepository getInstance(String commodityId) {
        if (instance == null) {
            instance = new MarketRepository(commodityId);
        }
        return instance;
    }

    public List<MarketApiWrapper> getBuyMarketByCommodity(String commodityId) {
        return buyMarkets.get(commodityId);
    }

    public List<MarketApiWrapper> getSellMarketByCommodity(String commodityId) {
        return sellMarkets.get(commodityId);
    }

    public List<MarketApiWrapper> getMarketByCommodityIdAndMarket(String commodityId, CommodityTab marketType) {
        if (marketType == CommodityTab.BUY) {
            return buyMarkets.get(commodityId);
        } else if (marketType == CommodityTab.SELL) {
            return sellMarkets.get(commodityId);
        }
        return buyMarkets.get(commodityId);
    }
}
