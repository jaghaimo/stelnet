package stelnet.commodity;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.RenderableState;
import stelnet.commodity.view.ButtonViewFactory;
import stelnet.commodity.view.DeleteViewFactory;
import stelnet.commodity.view.IntelViewFactory;
import stelnet.commodity.view.TabViewFactory;
import uilib.Renderable;
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
    public List<Renderable> toRenderables(Size size) {
        return Arrays.<Renderable>asList(
            new TabViewFactory(this).createContainer(size),
            new IntelViewFactory(this).createContainer(size),
            new ButtonViewFactory(this).createContainer(size),
            new DeleteViewFactory(this).createContainer(size)
        );
    }
}
