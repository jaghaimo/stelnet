package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.Query;
import stelnet.util.L10n;
import uilib.EventHandler;

public class OnOffButton extends ControlButton {

    public OnOffButton(final Query query) {
        super(L10n.query("ENABLED"), L10n.query("DISABLED"), true, query.isEnabled());
        setCutStyle(CutStyle.C2_MENU);
        scaleButton(query);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    query.setEnabled(!query.isEnabled());
                    query.refresh();
                }
            }
        );
    }
}
