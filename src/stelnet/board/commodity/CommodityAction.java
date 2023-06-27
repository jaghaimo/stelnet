package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import stelnet.board.commodity.market.BuyMarketFactory;
import stelnet.board.commodity.market.SellMarketFactory;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;

public enum CommodityAction {
    BUY {
        @Override
        public List<MarketAPI> getMarkets(final String commodityId) {
            return getBuyMarkets(commodityId);
        }

        @Override
        public Price getPrice(final String commodityId) {
            return new SupplyPrice(commodityId);
        }
    },
    SELL {
        @Override
        public List<MarketAPI> getMarkets(final String commodityId) {
            return getSellMarkets(commodityId);
        }

        @Override
        public Price getPrice(final String commodityId) {
            return new DemandPrice(commodityId);
        }
    },

    PROFIT {};

    public String key() {
        return "ACTION_" + name();
    }

    public List<MarketAPI> getMarkets(final String commodityId) {
        return Collections.emptyList();
    }

    public List<MarketAPI> getSellMarkets(final String commodityId) {
        return new SellMarketFactory(commodityId).createMarkets();
    }

    public List<MarketAPI> getBuyMarkets(final String commodityId) {
        return new BuyMarketFactory(commodityId).createMarkets();
    }

    public Price getPrice(final String commodityId) {
        return null;
    }
}
