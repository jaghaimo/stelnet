package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;

public class DeleteAllButton extends GlobalButton {

    public DeleteAllButton(final QueryManager manager, boolean isEnabled) {
        super("Delete All");
        setEnabled(isEnabled);
        setTextColor(Misc.getNegativeHighlightColor());
        setBackgroundColor(Misc.getNegativeHighlightColor());
        scaleBackground(0.5f);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    manager.deleteAll();
                }

                @Override
                public void onPrompt(TooltipMakerAPI tooltipMaker) {
                    tooltipMaker.addPara("Are you sure you want to delete ALL queries?", 0);
                }

                @Override
                public boolean hasPrompt() {
                    return true;
                }
            }
        );
    }
}
