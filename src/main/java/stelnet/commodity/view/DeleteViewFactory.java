package stelnet.commodity.view;

import uilib.Renderable;
import uilib.VerticalViewContainer;
import uilib.property.Size;

public class DeleteViewFactory {

    public Renderable createContainer(String activeId, Size size) {
        VerticalViewContainer verticalContainer = new VerticalViewContainer(
            new DeleteAllIntel(),
            new DeleteCommodityIntel(activeId)
        );
        verticalContainer.setSize(size);
        return verticalContainer;
    }
}
