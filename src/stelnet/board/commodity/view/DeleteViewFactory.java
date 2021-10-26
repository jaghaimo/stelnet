package stelnet.board.commodity.view;

import lombok.RequiredArgsConstructor;
import stelnet.board.commodity.CommodityState;
import stelnet.board.commodity.view.button.DeleteAllIntel;
import stelnet.board.commodity.view.button.DeleteCommodityIntel;
import uilib.Renderable;
import uilib.VerticalViewContainer;
import uilib.ViewContainerFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class DeleteViewFactory implements ViewContainerFactory {

    private final String activeId;

    public DeleteViewFactory(CommodityState commodityState) {
        this(commodityState.getCommodityId());
    }

    @Override
    public Renderable createContainer(Size size) {
        VerticalViewContainer verticalContainer = new VerticalViewContainer(
            new DeleteAllIntel(),
            new DeleteCommodityIntel(activeId)
        );
        verticalContainer.setSize(size);
        return verticalContainer;
    }
}
