package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import stelnet.util.L10n;
import uilib.EventHandler;

public class OnOffButton extends ControlButton {

    public OnOffButton(final Query query) {
        super(L10n.get(QueryL10n.ENABLED), L10n.get(QueryL10n.DISABLED), true, query.isEnabled());
        setCutStyle(CutStyle.C2_MENU);
        scaleButton(query);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.toggle();
                }
            }
        );
    }
}
