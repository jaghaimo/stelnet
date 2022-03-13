package stelnet.board.commodity.price;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.util.StelnetHelper;

public class DemandPrice implements Price {

    private final String commodityId;
    private final float econUnit;

    public DemandPrice(String commodityId) {
        this.commodityId = commodityId;
        this.econUnit = StelnetHelper.getCommoditySpec(commodityId).getEconUnit();
    }

    @Override
    public float getUnitPrice(MarketAPI market) {
        return market.getDemandPrice(commodityId, econUnit, true) / econUnit;
    }

    @Override
    public float getTotalPrice(MarketAPI market, int quantity) {
        return market.getDemandPrice(commodityId, quantity, true);
    }
}
