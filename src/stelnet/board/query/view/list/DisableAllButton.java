package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryManager;
import stelnet.util.L10n;
import uilib.EventHandler;

public class DisableAllButton extends GlobalButton {

    public DisableAllButton(final QueryManager manager, final boolean isEnabled, final float width) {
        super(L10n.query("DISABLE_ALL"), width);
        setEnabled(isEnabled);
        setShortcut(Keyboard.KEY_D);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    manager.setAllEnabled(false);
                }
            }
        );
    }
}
