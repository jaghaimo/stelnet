package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.market.ViewerBoard;
import stelnet.storage.SubmarketDataRenderer;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class ViewerTabButton extends TabButton {

    public ViewerTabButton(final SubmarketDataRenderer storageTab, boolean isActive, int shortcut) {
        super(L10n.get("storageTab" + storageTab.title), isActive, shortcut);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                ViewerBoard board = ViewerBoard.getInstance();
                board.setActiveTab(storageTab);
            }
        });
    }
}
