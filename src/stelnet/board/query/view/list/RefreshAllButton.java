package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;

public class RefreshAllButton extends GlobalButton {

    public RefreshAllButton(final QueryManager manager, boolean isEnabled) {
        super("Refresh All");
        setEnabled(isEnabled);
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
