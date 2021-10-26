package stelnet.board.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.storage.StorageBoard;
import stelnet.util.L10n;
import stelnet.view.market.ContentRenderer;
import uilib.EventHandler;
import uilib.TabButton;

public class StorageTabButton extends TabButton {

    public StorageTabButton(final ContentRenderer newRenderer, boolean isActive, int shortcut) {
        super(L10n.get("storageTab" + newRenderer.id), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance();
                    board.getState().setActiveRenderer(newRenderer);
                }
            }
        );
    }
}