package stelnet.board.commodity;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import stelnet.util.StelnetHelper;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
@Log4j
public class CommodityState implements RenderableState {

    private String commodityId = Commodities.SUPPLIES;
    private CommodityAction activeTab = CommodityAction.BUY;
    private transient IntelTracker intelTracker = new IntelTracker();

    public Object readResolve() {
        intelTracker = new IntelTracker();
        correctSelectedCommodity();
        return this;
    }

    @Override
    public List<Renderable> toRenderableList(final Size size) {
        return new CommodityView(commodityId, activeTab, intelTracker).create(size);
    }

    /**
     * The currently selected commodity id could no longer be valid if player removed a mod mid-save.
     * Make sure what we display does exist, or default to supplies.
     */
    private void correctSelectedCommodity() {
        if (!StelnetHelper.hasCommodity(commodityId)) {
            log.warn("Commodity with id " + commodityId + " no longer exists, resetting to supplies");
            commodityId = Commodities.SUPPLIES;
        }
    }
}
