package stelnet.commodity.extractor;

import stelnet.commodity.CommodityBoard.CommodityTab;

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
