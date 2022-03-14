package stelnet.board.commodity;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.log4j.Log4j;

@Log4j
public class IntelTracker {

    private final Map<String, CommodityIntel> intelMap = new LinkedHashMap<>();

    public void restore() {
        intelMap.clear();
        for (IntelInfoPlugin intel : Global.getSector().getIntelManager().getIntel(CommodityIntel.class)) {
            CommodityIntel elevatedIntel = (CommodityIntel) intel;
            CommoditySpecAPI commodity = elevatedIntel.getCommodity();
            String key = getKey(commodity.getId(), elevatedIntel.getMarket());
            intelMap.put(key, elevatedIntel);
        }
    }

    public boolean has(String commodityId) {
        Set<String> keys = new LinkedHashSet<>(intelMap.keySet());
        for (String key : keys) {
            if (intelMap.get(key).getCommodityId().equals(commodityId)) {
                return true;
            }
        }
        return false;
    }

    public boolean has(String commodityId, MarketAPI market) {
        String key = getKey(commodityId, market);
        CommodityIntel intel = intelMap.get(key);
        return intel != null;
    }

    /**
     * Removes one intel.
     */
    public void remove(CommodityIntel intel) {
        String key = getKey(intel.getCommodityId(), intel.getMarket());
        removeIntel(intel, key);
    }

    /**
     * Removes all intel.
     */
    public void remove() {
        for (CommodityIntel intel : intelMap.values()) {
            Global.getSector().getIntelManager().removeIntel(intel);
        }
        intelMap.clear();
    }

    /**
     * Removes all intel for a given `commodityId`.
     */
    public void remove(String commodityId) {
        Set<String> keys = new LinkedHashSet<>(intelMap.keySet());
        for (String key : keys) {
            removeIfCommodity(key, commodityId);
        }
    }

    public int size() {
        return intelMap.size();
    }

    public void toggle(String commodityId, MarketAPI market) {
        String key = getKey(commodityId, market);
        CommodityIntel intel = intelMap.get(key);
        if (intel == null) {
            addIntel(commodityId, market);
        } else {
            removeIntel(intel, key);
        }
    }

    private void addIntel(String commodityId, MarketAPI market) {
        String key = getKey(commodityId, market);
        CommoditySpecAPI commodity = Global.getSector().getEconomy().getCommoditySpec(commodityId);
        CommodityIntel intel = new CommodityIntel(commodity, this, market);
        Global.getSector().getIntelManager().addIntel(intel, true);
        intelMap.put(key, intel);
        log.debug("Added new intel with key " + key);
    }

    private String getKey(String commodityId, MarketAPI market) {
        return commodityId + ":" + market.getName();
    }

    private void removeIfCommodity(String key, String commodityId) {
        CommodityIntel intel = intelMap.get(key);
        if (intel.getCommodityId().equals(commodityId)) {
            removeIntel(intel, key);
        }
    }

    private void removeIntel(CommodityIntel intel, String key) {
        Global.getSector().getIntelManager().removeIntel(intel);
        intelMap.remove(key);
        log.debug("Removed existing intel with key " + key);
    }
}
