package stelnet.commodity.view;

import stelnet.ui.AbstractRenderable;
import stelnet.ui.Size;
import stelnet.ui.VerticalViewContainer;

public class DeleteViewFactory {

    public AbstractRenderable createContainer(String activeId, Size size) {
        // @formatter:off
        AbstractRenderable verticalContainer = new VerticalViewContainer(
                new DeleteAllIntel(),
                new DeleteCommodityIntel(activeId)
        );
        // @formatter:on
        verticalContainer.setSize(size);
        return verticalContainer;
    }
}
