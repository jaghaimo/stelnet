package stelnet.commodity.view;

import stelnet.ui.Renderable;
import stelnet.ui.Size;
import stelnet.ui.Stack;

public class DeleteViewFactory {

    public Renderable get(String activeId, Size size) {
        Renderable stack = new Stack(new PurgeButton(), new DeleteButton(activeId));
        stack.setSize(size);
        return stack;
    }
}
