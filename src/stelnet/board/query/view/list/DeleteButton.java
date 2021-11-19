package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import stelnet.util.L10n;
import uilib.EventHandler;

public class DeleteButton extends ControlButton {

    public DeleteButton(final Query query) {
        super(L10n.get(QueryL10n.DELETE), L10n.get(QueryL10n.DELETE), true, true);
        setTextColor(Misc.getNegativeHighlightColor());
        setBackgroundColor(Misc.getNegativeHighlightColor());
        setBackgroundSelectedColor(Misc.getNegativeHighlightColor());
        scaleBackground(0.5f);
        scaleButton(query);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    query.delete();
                }
            }
        );
    }
}
