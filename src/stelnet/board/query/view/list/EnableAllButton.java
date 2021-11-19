package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;

public class EnableAllButton extends GlobalButton {

    public EnableAllButton(final QueryManager manager, boolean isEnabled) {
        super("Enable All");
        setEnabled(isEnabled);
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
