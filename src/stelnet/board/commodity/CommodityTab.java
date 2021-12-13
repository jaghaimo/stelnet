package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import stelnet.board.commodity.market.BuyMarketFactory;
import stelnet.board.commodity.market.SellMarketFactory;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;

public enum CommodityTab {
    BUY {
        @Override
        public List<MarketAPI> getMarkets(String commodityId) {
            return new BuyMarketFactory(commodityId).createMarkets();
        }

        @Override
        public Price getPrice(String commodityId) {
            return new SupplyPrice(commodityId);
        }
    },
    SELL {
        @Override
        public List<MarketAPI> getMarkets(String commodityId) {
            return new SellMarketFactory(commodityId).createMarkets();
        }

        @Override
        public Price getPrice(String commodityId) {
            return new DemandPrice(commodityId);
        }
    };

    public List<MarketAPI> getMarkets(String commodityId) {
        return Collections.emptyList();
    }

    public Price getPrice(String commodityId) {
        return new Price() {
            @Override
            public float getPriceAmount(MarketAPI market) {
                return 0;
            }

            @Override
            public float getPriceAmount(MarketAPI market, int quantity) {
                return 0;
            }
        };
    }
}
