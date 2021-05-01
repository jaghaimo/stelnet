package stelnet.commodity.data;

import stelnet.commodity.CommodityTab;

public class PriceFactory {

    public Price get(String commodityId, CommodityTab commodityTab) {
        Price price = null;
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
