package stelnet.board.storage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.L10n;
import stelnet.widget.viewer.ContentRenderer;
import uilib.EventHandler;
import uilib.TabButton;

public class StorageTabButton extends TabButton {

    public StorageTabButton(final ContentRenderer newRenderer, boolean isActive, int shortcut) {
        super(L10n.get(newRenderer), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance(StorageBoard.class);
                    board.getRenderableState().setContentRenderer(newRenderer);
                }
            }
        );
    }
}
