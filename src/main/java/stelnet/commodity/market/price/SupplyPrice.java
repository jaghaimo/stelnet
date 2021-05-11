package stelnet.commodity.market.price;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.helper.GlobalHelper;

public class SupplyPrice implements Price {

    private final String commodityId;
    private final float econUnit;

    public SupplyPrice(String commodityId) {
        this.commodityId = commodityId;
        this.econUnit = GlobalHelper.getCommoditySpec(commodityId).getEconUnit();
    }

    @Override
    public float getPriceAmount(MarketAPI market) {
        return market.getSupplyPrice(commodityId, econUnit, true) / econUnit;
    }

    @Override
    public float getPriceAmount(MarketAPI market, int quantity) {
        return market.getSupplyPrice(commodityId, quantity, true);
    }
}
