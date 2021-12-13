package stelnet.board.commodity;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class CommodityState implements RenderableState {

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

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return new CommodityView(commodityId, activeTab, intelTracker).create(size);
    }
}
