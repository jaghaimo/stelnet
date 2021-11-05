package stelnet.board.commodity.view.button;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityL10n;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Size;

public class DeleteAllIntel extends Button {

    public DeleteAllIntel() {
        super(new Size(200, 24), L10n.get(CommodityL10n.DELETE_ALL), true, Misc.getNegativeHighlightColor());
        setLocation(Location.BOTTOM_RIGHT);
        setHandler(
            new EventHandler() {
                @Override
                public boolean hasPrompt() {
                    return true;
                }

                @Override
                public void onConfirm(IntelUIAPI ui) {
                    CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
                    board.getState().deleteIntel();
                }

                @Override
                public void onPrompt(TooltipMakerAPI tooltipMaker) {
                    tooltipMaker.addPara(L10n.get(CommodityL10n.DELETE_ALL_CONFIRMATION), Misc.getTextColor(), 0f);
                }
            }
        );
    }
}
