package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.board.commodity.price.Price;
import stelnet.util.EconomyUtils;
import stelnet.util.IntelUtils;

@Log4j
public class IntelTracker {

    private transient Map<String, CommodityIntel> intelMap = new LinkedHashMap<>();

    public Object readResolve() {
        intelMap = new LinkedHashMap<>();
        for (IntelInfoPlugin intel : IntelUtils.getAll(CommodityIntel.class)) {
            CommodityIntel elevatedIntel = (CommodityIntel) intel;
            CommoditySpecAPI commodity = elevatedIntel.getCommodity();
            String key = getKey(elevatedIntel.getAction(), commodity.getId(), elevatedIntel.getMarket());
            intelMap.put(key, elevatedIntel);
        }
        return this;
    }

    public boolean has(String action, String commodityId, MarketAPI market) {
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = intelMap.get(key);
        return intel != null;
    }

    public void remove(CommodityIntel intel) {
        String key = getKey(intel.getAction(), intel.getCommodityId(), intel.getMarket());
        remove(intel, key);
    }

    public void removeAll() {
        for (CommodityIntel intel : intelMap.values()) {
            IntelUtils.remove(intel);
        }
        intelMap.clear();
    }

    public void removeCommodity(String commodityId) {
        Set<String> keys = new LinkedHashSet<>(intelMap.keySet());
        for (String key : keys) {
            removeIfCommodity(key, commodityId);
        }
    }

    public void toggle(String commodityId, CommodityTab commodityTab, MarketAPI market) {
        String action = commodityTab.name();
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = intelMap.get(key);
        if (intel == null) {
            add(commodityId, commodityTab, market);
        } else {
            remove(intel, key);
        }
    }

    private void add(String commodityId, CommodityTab commodityTab, MarketAPI market) {
        String action = commodityTab.name();
        String key = getKey(action, commodityId, market);
        CommoditySpecAPI commodity = EconomyUtils.getCommoditySpec(commodityId);
        Price price = commodityTab.getPrice(commodityId);
        CommodityIntel intel = new CommodityIntel(action, commodity, market, price);
        IntelUtils.add(intel, true);
        intelMap.put(key, intel);
        log.debug("Added new intel with key " + key);
    }

    private String getKey(String action, String commodityId, MarketAPI market) {
        return action + ":" + commodityId + ":" + market.getName();
    }

    private void remove(CommodityIntel intel, String key) {
        IntelUtils.remove(intel);
        intelMap.remove(key);
        log.debug("Removed existing intel with key " + key);
    }

    private void removeIfCommodity(String key, String commodityId) {
        CommodityIntel intel = intelMap.get(key);
        if (intel.getCommodityId().equals(commodityId)) {
            remove(intel, key);
        }
    }
}
