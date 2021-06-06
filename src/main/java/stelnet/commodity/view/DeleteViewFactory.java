package stelnet.commodity.view;

import stelnet.ui.AbstractRenderable;
import stelnet.ui.Renderable;
import stelnet.ui.VerticalViewContainer;
import stelnet.ui.property.Size;

public class DeleteViewFactory {

    public Renderable createContainer(String activeId, Size size) {
        AbstractRenderable verticalContainer = new VerticalViewContainer(
                new DeleteAllIntel(),
                new DeleteCommodityIntel(activeId)
        );
        verticalContainer.setSize(size);
        return verticalContainer;
    }
}
