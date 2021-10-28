package stelnet.board.viewer;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.L10n;
import stelnet.widget.market.ContentRenderer;
import uilib.EventHandler;
import uilib.TabButton;

public class ViewerTabButton extends TabButton {

    public ViewerTabButton(final ContentRenderer newRenderer, boolean isActive, int shortcut) {
        super(L10n.get("storageTab" + newRenderer.id), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    ViewerBoard board = ViewerBoard.getInstance();
                    board.getState().setActiveRenderer(newRenderer);
                }
            }
        );
    }
}
