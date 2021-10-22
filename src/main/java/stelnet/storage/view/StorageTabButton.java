package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.storage.StorageBoard;
import stelnet.storage.SubmarketDataRenderer;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class StorageTabButton extends TabButton {

    public StorageTabButton(
        final SubmarketDataRenderer storageTab,
        boolean isActive,
        int shortcut
    ) {
        super(L10n.get("storageTab" + storageTab.title), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance();
                    board.setActiveTab(storageTab);
                }
            }
        );
    }
}
