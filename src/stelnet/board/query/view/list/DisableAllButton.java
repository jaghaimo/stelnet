package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;

public class DisableAllButton extends GlobalButton {

    public DisableAllButton(final QueryManager manager, boolean isEnabled, float width) {
        super(QueryL10n.DISABLE_ALL, width);
        setEnabled(isEnabled);
        setShortcut(Keyboard.KEY_D);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    manager.setAllEnabled(false);
                }
            }
        );
    }
}
