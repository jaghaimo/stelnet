package stelnet.board.commodity.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import lombok.Getter;
import stelnet.board.commodity.CommodityState.CommodityTab;

@Getter
public class MarketRepository {

    List<MarketAPI> buyMarkets;
    List<MarketAPI> sellMarkets;

    public MarketRepository(String commodityId) {
        buyMarkets = new BuyMarketFactory(commodityId).createMarkets();
        sellMarkets = new SellMarketFactory(commodityId).createMarkets();
    }

    public List<MarketAPI> getMarketsByType(CommodityTab marketType) {
        if (marketType == CommodityTab.SELL) {
            return sellMarkets;
        }
        return buyMarkets;
    }
}
