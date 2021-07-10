package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.L10n;
import stelnet.market.MarketViewBoard;
import stelnet.storage.StorageTab;
import stelnet.ui.EventHandler;
import stelnet.ui.TabButton;

public class ViewerTabButton extends TabButton {

    public ViewerTabButton(final StorageTab storageTab, boolean isActive, int shortcut) {
        super(L10n.get("storageTab" + storageTab.title), isActive, shortcut);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                MarketViewBoard board = MarketViewBoard.getInstance();
                board.setActiveTab(storageTab);
            }
        });
    }
}
