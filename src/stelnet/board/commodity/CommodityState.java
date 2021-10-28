package stelnet.board.commodity;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.commodity.view.ButtonViewFactory;
import stelnet.board.commodity.view.DeleteViewFactory;
import stelnet.board.commodity.view.IntelViewFactory;
import stelnet.board.commodity.view.TabViewFactory;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class CommodityState implements RenderableState {

    public static enum CommodityTab {
        BUY("Buy"),
        SELL("Sell"),
        PROFIT("Profit");

        public final String id;

        private CommodityTab(String id) {
            this.id = id;
        }
    }

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
            new TabViewFactory(this).create(size),
            new IntelViewFactory(this).create(size),
            new ButtonViewFactory(this).create(size),
            new DeleteViewFactory(this).create(size)
        );
    }
}
