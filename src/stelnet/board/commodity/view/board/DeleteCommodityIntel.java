package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.commodity.CommodityL10n;
import stelnet.board.commodity.IntelTracker;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.UiConstants;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class DeleteCommodityIntel extends C2Button {

    private final String commodityId;
    private final IntelTracker intelTracker;

    public DeleteCommodityIntel(String commodityId, IntelTracker intelTracker) {
        super(new Size(190, UiConstants.DEFAULT_BUTTON_HEIGHT), L10n.get(CommodityL10n.DELETE_THIS), true);
        this.commodityId = commodityId;
        this.intelTracker = intelTracker;
        setEnabled(intelTracker.has(commodityId));
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(8, 1));
    }

    @Override
    public boolean hasPrompt() {
        return true;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        intelTracker.removeCommodity(commodityId);
    }

    @Override
    public void onPrompt(TooltipMakerAPI tooltipMaker) {
        tooltipMaker.addPara(L10n.get(CommodityL10n.DELETE_THIS_CONFIRMATION), Misc.getTextColor(), 0f);
    }
}
