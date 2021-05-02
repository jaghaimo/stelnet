package stelnet.commodity;

import java.util.HashMap;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;

import stelnet.commodity.market.MarketApiWrapper;
import stelnet.commodity.market.price.Price;
import stelnet.commodity.market.price.PriceFactory;
import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;

public class IntelTracker extends HashMap<String, CommodityIntel> {

    private static final long serialVersionUID = 1L;
    private final PriceFactory priceFactory;

    public IntelTracker() {
        super();
        priceFactory = new PriceFactory();
    }

    public void remove(CommodityIntel intel) {
        String key = getKey(intel.getAction(), intel.getCommodityId(), intel.getMarket());
        IntelHelper.removeIntel(intel);
        remove(key);
    }

    public boolean has(String action, String commodityId, MarketApiWrapper market) {
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        return intel != null;
    }

    public void toggle(String commodityId, CommodityTab commodityTab, MarketApiWrapper market) {
        String action = commodityTab.title;
        String key = getKey(action, commodityId, market);
        CommodityIntel intel = get(key);
        if (intel == null) {
            CommoditySpecAPI commodity = GlobalHelper.getCommoditySpec(commodityId);
            Price price = priceFactory.get(commodityId, commodityTab);
            intel = new CommodityIntel(action, commodity, market, this, price);
            IntelHelper.addIntel(intel, true);
            put(key, intel);
        } else {
            IntelHelper.removeIntel(intel);
            remove(key);
        }
    }

    private String getKey(String action, String commodityId, MarketApiWrapper market) {
        return action + ":" + commodityId + ":" + market.getName();
    }
}
