package stelnet.commodity.market;

import stelnet.commodity.data.BuyMarketFactory;
import stelnet.commodity.data.SellMarketFactory;

import java.util.HashMap;
import java.util.List;

public class MarketRepository {

    HashMap<String, List<MarketApiWrapper>> buyMarkets = new HashMap<>();
    HashMap<String, List<MarketApiWrapper>> sellMarkets = new HashMap<>();

    public MarketRepository(String commodityId) {
        List<MarketApiWrapper> buyMarket = new BuyMarketFactory(commodityId).createMarkets();
        List<MarketApiWrapper> sellMarket = new SellMarketFactory(commodityId).createMarkets();

        buyMarkets.put(commodityId, buyMarket);
        sellMarkets.put(commodityId, sellMarket);
    }

    public List<MarketApiWrapper> getBuyMarketByCommodity(String commodityId) {
        return buyMarkets.get(commodityId);
    }

    public List<MarketApiWrapper> getSellMarketByCommodity(String commodityId) {
        return sellMarkets.get(commodityId);
    }
}
