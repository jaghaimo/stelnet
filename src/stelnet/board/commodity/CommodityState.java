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
    private CommodityAction activeTab = CommodityAction.BUY;
    private transient IntelTracker intelTracker = new IntelTracker();

    public Object readResolve() {
        intelTracker = new IntelTracker();
        return this;
    }

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return new CommodityView(commodityId, activeTab, intelTracker).create(size);
    }
}
