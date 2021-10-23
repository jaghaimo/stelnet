package stelnet.commodity;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityState {

    private String commodityId = Commodities.SUPPLIES;
    private CommodityTab activeTab = CommodityTab.BUY;
    private final IntelTracker intelTracker = new IntelTracker();

    public void deleteIntel() {
        intelTracker.removeAll();
    }

    public void deleteIntel(CommodityIntel intel) {
        intelTracker.remove(intel);
    }

    public void deleteIntel(String commodityId) {
        intelTracker.removeCommodity(commodityId);
    }
}
