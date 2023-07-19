package stelnet.board.storage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import stelnet.widget.viewer.ContentRenderer;
import uilib.EventHandler;
import uilib.TabButton;

public class StorageTabButton extends TabButton {

    public StorageTabButton(final ContentRenderer newRenderer, final boolean isActive, final int shortcut) {
        super(L10n.widget(newRenderer.name()), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    final StorageBoard board = StelnetHelper.getInstance(StorageBoard.class);
                    board.getRenderableState().setContentRenderer(newRenderer);
                }
            }
        );
    }
}
