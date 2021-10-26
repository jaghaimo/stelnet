package stelnet.board.commodity.market.price;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.util.Economy;

public class DemandPrice implements Price {

    private final String commodityId;
    private final float econUnit;

    public DemandPrice(String commodityId) {
        this.commodityId = commodityId;
        this.econUnit = Economy.getCommoditySpec(commodityId).getEconUnit();
    }

    @Override
    public float getPriceAmount(MarketAPI market) {
        return market.getDemandPrice(commodityId, econUnit, true) / econUnit;
    }

    @Override
    public float getPriceAmount(MarketAPI market, int quantity) {
        return market.getDemandPrice(commodityId, quantity, true);
    }
}
