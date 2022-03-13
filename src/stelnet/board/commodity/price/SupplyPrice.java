package stelnet.board.commodity.price;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class SupplyPrice implements Price {

    private final String commodityId;
    private final float econUnit;

    public SupplyPrice(String commodityId) {
        this.commodityId = commodityId;
        this.econUnit = Global.getSector().getEconomy().getCommoditySpec(commodityId).getEconUnit();
    }

    @Override
    public float getUnitPrice(MarketAPI market) {
        return market.getSupplyPrice(commodityId, econUnit, true) / econUnit;
    }

    @Override
    public float getTotalPrice(MarketAPI market, int quantity) {
        return market.getSupplyPrice(commodityId, quantity, true);
    }
}
