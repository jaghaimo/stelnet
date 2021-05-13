package stelnet.commodity;

import java.util.HashMap;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;

import lombok.extern.log4j.Log4j;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.price.Price;
import stelnet.helper.GlobalSectorHelper;
import stelnet.helper.IntelHelper;

@Log4j
public class IntelTracker extends HashMap<String, CommodityIntel> {

    private static final long serialVersionUID = 1L;

    public boolean has(String action, String commodityId, MarketApiWrapper market) {
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        return intel != null;
    }

    public void removeAll() {
        for (CommodityIntel intel : values()) {
            IntelHelper.removeIntel(intel);
        }
        clear();
    }

    public void removeCommodity(String commodityId) {
        for (CommodityIntel intel : values()) {
            if (intel.getCommodityId().equals(commodityId)) {
                remove(intel);
            }
        }
    }

    public void remove(CommodityIntel intel) {
        String key = getKey(intel.getAction(), intel.getCommodityId(), intel.getMarketWrapper());
        IntelHelper.removeIntel(intel);
        remove(key);
    }

    public void toggle(String commodityId, CommodityTab commodityTab, MarketApiWrapper market) {
        String action = commodityTab.getTitle();
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        if (intel == null) {
            log.debug("Adding new intel with key " + key);
            CommoditySpecAPI commodity = GlobalSectorHelper.getCommoditySpec(commodityId);
            Price price = market.getPrice();
            intel = new CommodityIntel(action, commodity, market, price);
            IntelHelper.addIntel(intel, true);
            put(key, intel);
        } else {
            log.debug("Removing existing intel with key " + key);
            IntelHelper.removeIntel(intel);
            remove(key);
        }
    }

    private String getKey(String action, String commodityId, MarketApiWrapper market) {
        return action + ":" + commodityId + ":" + market.getName();
    }
}
