package stelnet.board.commodity;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class CommodityState implements RenderableState {

    public static enum CommodityTab {
        BUY {
            @Override
            public Price getPrice(String commodityId) {
                return new SupplyPrice(commodityId);
            }
        },
        SELL {
            @Override
            public Price getPrice(String commodityId) {
                return new DemandPrice(commodityId);
            }
        };

        public Price getPrice(String commodityId) {
            return null;
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
    public List<Renderable> toRenderableList(Size size) {
        return new CommodityView(commodityId, activeTab, intelTracker).create(size);
    }
}
