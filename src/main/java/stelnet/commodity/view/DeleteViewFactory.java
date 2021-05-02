package stelnet.commodity.view;

import stelnet.ui.AbstractRenderable;
import stelnet.ui.Size;
import stelnet.ui.VerticalViewContainer;

public class DeleteViewFactory {

    public AbstractRenderable createContainer(String activeId, Size size) {
        AbstractRenderable verticalContainer = new VerticalViewContainer(new PurgeButton(), new DeleteButton(activeId));
        verticalContainer.setSize(size);
        return verticalContainer;
    }
}
