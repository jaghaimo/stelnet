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
import stelnet.util.StelnetHelper;

@Log4j
public class IntelTracker {

    private final Map<String, CommodityIntel> intelMap = new LinkedHashMap<>();

    public void restore() {
        intelMap.clear();
        for (final IntelInfoPlugin intel : Global.getSector().getIntelManager().getIntel(CommodityIntel.class)) {
            final CommodityIntel elevatedIntel = (CommodityIntel) intel;
            final CommoditySpecAPI commodity = elevatedIntel.getCommodity();
            final String key = getKey(commodity.getId(), elevatedIntel.getMarket());
            intelMap.put(key, elevatedIntel);
        }
    }

    public boolean has(final String commodityId) {
        final Set<String> keys = new LinkedHashSet<>(intelMap.keySet());
        for (final String key : keys) {
            if (intelMap.get(key).getCommodityId().equals(commodityId)) {
                return true;
            }
        }
        return false;
    }

    public boolean has(final String commodityId, final MarketAPI market) {
        final String key = getKey(commodityId, market);
        final CommodityIntel intel = intelMap.get(key);
        return intel != null;
    }

    /**
     * Removes one intel.
     */
    public void remove(final CommodityIntel intel) {
        final String key = getKey(intel.getCommodityId(), intel.getMarket());
        removeIntel(intel, key);
    }

    /**
     * Removes all intel.
     */
    public void remove() {
        for (final CommodityIntel intel : intelMap.values()) {
            Global.getSector().getIntelManager().removeIntel(intel);
        }
        intelMap.clear();
    }

    /**
     * Removes all intel for a given `commodityId`.
     */
    public void remove(final String commodityId) {
        final Set<String> keys = new LinkedHashSet<>(intelMap.keySet());
        for (final String key : keys) {
            removeIfCommodity(key, commodityId);
        }
    }

    public int size() {
        return intelMap.size();
    }

    public void toggle(final String commodityId, final MarketAPI market) {
        final String key = getKey(commodityId, market);
        final CommodityIntel intel = intelMap.get(key);
        if (intel == null) {
            addIntel(commodityId, market);
        } else {
            removeIntel(intel, key);
        }
    }

    private void addIntel(final String commodityId, final MarketAPI market) {
        if (!StelnetHelper.hasCommodity(commodityId)) {
            log.warn("Could not get commodity spec for id " + commodityId + ", avoiding intel creation");
            return;
        }
        final CommodityIntel intel = new CommodityIntel(commodityId, this, market);
        Global.getSector().getIntelManager().addIntel(intel, true);
        final String key = getKey(commodityId, market);
        intelMap.put(key, intel);
        log.debug("Added new intel with key " + key);
    }

    private String getKey(final String commodityId, final MarketAPI market) {
        return commodityId + ":" + market.getName();
    }

    private void removeIfCommodity(final String key, final String commodityId) {
        final CommodityIntel intel = intelMap.get(key);
        if (intel.getCommodityId().equals(commodityId)) {
            removeIntel(intel, key);
        }
    }

    private void removeIntel(final CommodityIntel intel, final String key) {
        Global.getSector().getIntelManager().removeIntel(intel);
        intelMap.remove(key);
        log.debug("Removed existing intel with key " + key);
    }
}
