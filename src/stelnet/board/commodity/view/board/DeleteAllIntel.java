package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.commodity.IntelTracker;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.UiConstants;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class DeleteAllIntel extends C2Button {

    private final IntelTracker intelTracker;

    public DeleteAllIntel(final IntelTracker intelTracker) {
        super(
            new Size(190, UiConstants.DEFAULT_BUTTON_HEIGHT),
            L10n.commodity("DELETE_ALL"),
            true,
            Misc.getNegativeHighlightColor()
        );
        this.intelTracker = intelTracker;
        setEnabled(intelTracker.size() > 0);
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(8, 1));
    }

    @Override
    public boolean hasPrompt() {
        return true;
    }

    @Override
    public void onConfirm(final IntelUIAPI ui) {
        intelTracker.remove();
    }

    @Override
    public void onPrompt(final TooltipMakerAPI tooltipMaker) {
        tooltipMaker.addPara(L10n.commodity("DELETE_ALL_CONFIRMATION"), Misc.getTextColor(), 0f);
    }
}
