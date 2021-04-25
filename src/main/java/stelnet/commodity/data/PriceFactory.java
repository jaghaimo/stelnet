package stelnet.commodity.data;

import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.data.content.buy.SupplyPrice;
import stelnet.commodity.data.content.sell.DemandPrice;

public class PriceFactory {

    public Price get(String commodityId, CommodityTab commodityTab) {
        Price price = new DummyPrice();
        switch (commodityTab) {
        case BUY:
            price = new SupplyPrice(commodityId);
            break;

        case SELL:
            price = new DemandPrice(commodityId);
            break;
        }
        return price;
    }
}
