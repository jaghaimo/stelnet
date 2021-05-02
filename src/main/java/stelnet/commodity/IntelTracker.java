package stelnet.commodity;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.commodity.market.MarketRepository;
import stelnet.commodity.market.price.Price;
import stelnet.commodity.market.price.PriceFactory;
import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;

import java.util.HashMap;

public class IntelTracker extends HashMap<String, CommodityIntel> {

    private static final long serialVersionUID = 1L;
    private final PriceFactory priceFactory;
    private MarketRepository marketRepository;

    public IntelTracker(MarketRepository marketRepository) {
        super();
        this.marketRepository = marketRepository;
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

    private String getKey(String action, String commodityId, MarketAPI market) {
        return action + ":" + commodityId + ":" + market.getName();
    }
}
