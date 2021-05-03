package stelnet.commodity;

import java.util.HashMap;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;

import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.price.Price;
import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;
import stelnet.helper.LogHelper;

public class IntelTracker extends HashMap<String, CommodityIntel> {

    private static final long serialVersionUID = 1L;

    public boolean has(String action, String commodityId, MarketApiWrapper market) {
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        return intel != null;
    }

    public void remove(CommodityIntel intel) {
        String key = getKey(intel.getAction(), intel.getCommodityId(), intel.getMarketWrapper());
        IntelHelper.removeIntel(intel);
        remove(key);
    }

    public void toggle(String commodityId, CommodityTab commodityTab, MarketApiWrapper market) {
        String action = commodityTab.title;
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        if (intel == null) {
            LogHelper.debug("Adding new intel with key " + key);
            CommoditySpecAPI commodity = GlobalHelper.getCommoditySpec(commodityId);
            Price price = market.getPrice();
            intel = new CommodityIntel(action, commodity, market, this, price);
            IntelHelper.addIntel(intel, true);
            put(key, intel);
        } else {
            LogHelper.debug("Removing existing intel with key " + key);
            IntelHelper.removeIntel(intel);
            remove(key);
        }
    }

    private String getKey(String action, String commodityId, MarketApiWrapper market) {
        return action + ":" + commodityId + ":" + market.getName();
    }
}
