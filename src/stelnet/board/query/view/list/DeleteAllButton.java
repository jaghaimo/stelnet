package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.QueryManager;
import stelnet.util.L10n;
import uilib.EventHandler;

public class DeleteAllButton extends GlobalButton {

    public DeleteAllButton(final QueryManager manager, final boolean isEnabled, final float width) {
        super(L10n.query("DELETE_ALL"), width);
        setEnabled(isEnabled);
        setTextColor(Misc.getNegativeHighlightColor());
        setBackgroundColor(Misc.getNegativeHighlightColor());
        scaleBackground(0.5f);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    manager.deleteAll();
                }

                @Override
                public void onPrompt(final TooltipMakerAPI tooltipMaker) {
                    tooltipMaker.addPara(L10n.query("DELETE_ALL_CONFIRMATION"), 0);
                }

                @Override
                public boolean hasPrompt() {
                    return true;
                }
            }
        );
    }
}
