package stelnet.commodity.view;

import stelnet.ui.AbstractRenderable;
import stelnet.ui.Size;
import stelnet.ui.VerticalViewContainer;

public class DeleteViewFactory {

    public AbstractRenderable get(String activeId, Size size) {
        AbstractRenderable stack = new VerticalViewContainer(new PurgeButton(), new DeleteButton(activeId));
        stack.setSize(size);
        return stack;
    }
}
