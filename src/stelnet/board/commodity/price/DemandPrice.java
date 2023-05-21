package stelnet.board.commodity.price;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.extern.log4j.Log4j;

@Log4j
public class DemandPrice implements Price {

    private final String commodityId;
    private final float econUnit;

    public DemandPrice(String commodityId) {
        this.commodityId = commodityId;
        CommoditySpecAPI commodity = Global.getSector().getEconomy().getCommoditySpec(commodityId);
        float econUnit = 100;
        if (commodity != null) {
            econUnit = commodity.getEconUnit();
        }
        if (commodity == null) {
            log.warn("Could not get commodity spec for id " + commodityId + " - assuming 100 econUnit");
        }
        this.econUnit = econUnit;
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
