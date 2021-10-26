package stelnet.board.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.storage.StorageBoard;
import stelnet.board.storage.SubmarketDataRenderer;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class StorageTabButton extends TabButton {

    public StorageTabButton(final SubmarketDataRenderer storageTab, boolean isActive, int shortcut) {
        super(L10n.get("storageTab" + storageTab.id), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance();
                    board.getState().setActiveTab(storageTab);
                }
            }
        );
    }
}
