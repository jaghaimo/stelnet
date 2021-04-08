package stelnet.commodity;

import java.util.HashMap;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.extractor.Price;
import stelnet.commodity.extractor.PriceFactory;
import stelnet.helper.IntelHelper;

public class IntelTracker extends HashMap<String, CommodityIntel> {

    private static final long serialVersionUID = 1L;
    private PriceFactory priceFactory;

    public IntelTracker() {
        super();
        priceFactory = new PriceFactory();
    }

    public void remove(CommodityIntel intel) {
        String key = getKey(intel.getAction(), intel.getCommodityId(), intel.getMarket());
        IntelHelper.removeIntel(intel);
        remove(key);
    }

    public boolean has(String action, String commodityId, MarketAPI market) {
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        return intel != null;
    }

    public void toggle(String commodityId, CommodityTab commodityTab, MarketAPI market) {
        String action = commodityTab.title;
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        if (intel == null) {
            CommoditySpecAPI commodity = Global.getSector().getEconomy().getCommoditySpec(commodityId);
            Price price = priceFactory.get(commodityId, commodityTab);
            intel = new CommodityIntel(action, commodity, market, this, price);
            IntelHelper.addIntel(intel);
            put(key, intel);
        } else {
            IntelHelper.removeIntel(intel);
            remove(key);
        }
    }

    private String getKey(String action, String commodityId, MarketAPI market) {
        return action + ":" + commodityId + ":" + market.getName();
    }
}
