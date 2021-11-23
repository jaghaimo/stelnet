package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;

public class EnableAllButton extends GlobalButton {

    public EnableAllButton(final QueryManager manager, boolean isEnabled) {
        super(QueryL10n.ENABLE_ALL);
        setEnabled(isEnabled);
        setShortcut(Keyboard.KEY_E);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    manager.setAllEnabled(true);
                }
            }
        );
    }
}
