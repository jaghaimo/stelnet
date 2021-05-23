package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.L10n;
import stelnet.storage.StorageBoard;
import stelnet.storage.StorageTab;
import stelnet.ui.EventHandler;
import stelnet.ui.TabButton;

public class StorageTabButton extends TabButton {

    public StorageTabButton(final StorageTab storageTab, boolean isActive, int shortcut) {
        super(L10n.get("storageTab" + storageTab.title), isActive, shortcut);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.setActiveTab(storageTab);
            }
        });
    }
}
