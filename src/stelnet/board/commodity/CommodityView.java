package stelnet.board.commodity;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.board.commodity.view.ButtonViewFactory;
import stelnet.board.commodity.view.DeleteViewFactory;
import stelnet.board.commodity.view.IntelViewFactory;
import stelnet.board.commodity.view.TabViewFactory;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class CommodityView implements RenderableFactory {

    private final String commodityId;
    private final CommodityTab activeTab;
    private final IntelTracker intelTracker;

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        renderables.addAll(new TabViewFactory(commodityId, activeTab).create(size));
        renderables.addAll(new IntelViewFactory(commodityId, activeTab, intelTracker).create(size));
        renderables.addAll(new ButtonViewFactory(commodityId).create(size));
        renderables.addAll(new DeleteViewFactory(commodityId).create(size));
        return renderables;
    }
}
