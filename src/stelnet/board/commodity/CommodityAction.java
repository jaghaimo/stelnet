package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import stelnet.board.commodity.market.BuyMarketFactory;
import stelnet.board.commodity.market.SellMarketFactory;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.board.commodity.view.board.MultiMarketView;
import stelnet.board.commodity.view.board.SingleMarketView;
import uilib.RenderableFactory;

public enum CommodityAction {
    BUY {
        @Override
        public List<MarketAPI> getMarkets(String commodityId) {
            return getBuyMarkets(commodityId);
        }

        @Override
        public Price getPrice(String commodityId) {
            return new SupplyPrice(commodityId);
        }
    },
    SELL {
        @Override
        public List<MarketAPI> getMarkets(String commodityId) {
            return getSellMarkets(commodityId);
        }

        @Override
        public Price getPrice(String commodityId) {
            return new DemandPrice(commodityId);
        }
    },

    PROFIT {
        @Override
        public RenderableFactory getFactory(String commodityId, IntelTracker intelTracker) {
            return new MultiMarketView(commodityId, this, intelTracker);
        }

        @Override
        public Price getPrice(String commodityId) {
            return new DemandPrice(commodityId);
        }
    };

    public RenderableFactory getFactory(String commodityId, IntelTracker intelTracker) {
        return new SingleMarketView(commodityId, this, intelTracker);
    }

    public List<MarketAPI> getMarkets(String commodityId) {
        return Collections.emptyList();
    }

    public List<MarketAPI> getSellMarkets(String commodityId) {
        return new SellMarketFactory(commodityId).createMarkets();
    }

    public List<MarketAPI> getBuyMarkets(String commodityId) {
        return new BuyMarketFactory(commodityId).createMarkets();
    }

    public Price getPrice(String commodityId) {
        return null;
    }
}
