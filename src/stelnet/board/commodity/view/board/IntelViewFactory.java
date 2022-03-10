package stelnet.board.commodity.view.board;

import java.util.List;
import lombok.AllArgsConstructor;
import stelnet.board.commodity.CommodityAction;
import stelnet.board.commodity.IntelTracker;
import stelnet.board.commodity.view.board.profit.ProfitView;
import uilib.Renderable;
import uilib.property.Size;

@AllArgsConstructor
public class IntelViewFactory {

    private final String commodityId;
    private final CommodityAction commodityAction;
    private final IntelTracker tracker;

    public List<Renderable> create(Size size) {
        if (commodityAction == CommodityAction.PROFIT) {
            return new ProfitView(commodityId, commodityAction, tracker).create(size);
        } else {
            return new CommodityView(commodityId, commodityAction, tracker).create(size);
        }
    }
}
