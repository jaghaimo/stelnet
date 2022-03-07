package stelnet.board.commodity.view.board;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.VerticalViewContainer;
import uilib.property.Size;

@RequiredArgsConstructor
public class DeleteViewFactory implements RenderableFactory {

    private final String commodityId;

    @Override
    public List<Renderable> create(Size size) {
        VerticalViewContainer verticalContainer = new VerticalViewContainer(
            new DeleteAllIntel(),
            new DeleteCommodityIntel(commodityId)
        );
        verticalContainer.setSize(size);
        return Collections.<Renderable>singletonList(verticalContainer);
    }
}
