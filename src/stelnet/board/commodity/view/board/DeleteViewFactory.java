package stelnet.board.commodity.view.board;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.IntelTracker;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.VerticalViewContainer;
import uilib.property.Size;

@RequiredArgsConstructor
public class DeleteViewFactory implements RenderableFactory {

    private final String commodityId;
    private final IntelTracker intelTracker;

    @Override
    public List<Renderable> create(Size size) {
        VerticalViewContainer verticalContainer = new VerticalViewContainer(
            new DeleteAllIntel(intelTracker),
            new DeleteCommodityIntel(commodityId, intelTracker)
        );
        verticalContainer.setSize(size);
        return Collections.<Renderable>singletonList(verticalContainer);
    }
}
