package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.board.commodity.market.MarketApiWrapper;
import stelnet.board.commodity.market.price.Price;
import stelnet.util.Economy;
import stelnet.util.IntelManager;

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
            IntelManager.removeIntel(intel);
        }
        clear();
    }

    public void removeCommodity(String commodityId) {
        Set<String> keys = new HashSet<>(keySet());
        for (String key : keys) {
            CommodityIntel intel = get(key);
            if (intel.getCommodityId().equals(commodityId)) {
                remove(intel);
            }
        }
    }

    public void remove(CommodityIntel intel) {
        String key = getKey(intel.getAction(), intel.getCommodityId(), intel.getMarketWrapper());
        IntelManager.removeIntel(intel);
        remove(key);
    }

    public void toggle(String commodityId, CommodityTab commodityTab, MarketApiWrapper market) {
        String action = commodityTab.id;
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        if (intel == null) {
            log.debug("Adding new intel with key " + key);
            CommoditySpecAPI commodity = Economy.getCommoditySpec(commodityId);
            Price price = market.getPrice();
            intel = new CommodityIntel(action, commodity, market, price);
            IntelManager.addIntel(intel, true);
            put(key, intel);
        } else {
            log.debug("Removing existing intel with key " + key);
            IntelManager.removeIntel(intel);
            remove(key);
        }
    }

    private String getKey(String action, String commodityId, MarketApiWrapper market) {
        return action + ":" + commodityId + ":" + market.getName();
    }
}
