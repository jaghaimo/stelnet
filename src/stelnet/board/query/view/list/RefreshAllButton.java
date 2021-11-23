package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;

public class RefreshAllButton extends GlobalButton {

    public RefreshAllButton(final QueryManager manager, boolean isEnabled) {
        super(QueryL10n.REFRESH_ALL);
        setEnabled(isEnabled);
        setShortcut(Keyboard.KEY_R);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    manager.updateIntel();
                }
            }
        );
    }
}
