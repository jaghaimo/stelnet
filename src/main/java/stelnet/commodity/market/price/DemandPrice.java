package stelnet.commodity.market.price;

import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.helper.GlobalHelper;

public class DemandPrice implements Price {

    private final String commodityId;
    private final float econUnit;

    public DemandPrice(String commodityId) {
        this.commodityId = commodityId;
        EconomyAPI economy = GlobalHelper.getEconomy();
        this.econUnit = economy.getCommoditySpec(commodityId).getEconUnit();
    }

    @Override
    public float getPriceAmount(MarketAPI market) {
        return market.getDemandPrice(commodityId, econUnit, true) / econUnit;
    }
}
