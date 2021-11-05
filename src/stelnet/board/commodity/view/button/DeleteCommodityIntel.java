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
import uilib.property.Position;
import uilib.property.Size;

public class DeleteCommodityIntel extends Button {

    public DeleteCommodityIntel(final String commodityId) {
        super(new Size(190, 24), L10n.get(CommodityL10n.DELETE_THIS), true, Misc.getButtonTextColor());
        setLocation(Location.BOTTOM_RIGHT);
        setOffset(new Position(8, 1));
        setHandler(
            new EventHandler() {
                @Override
                public boolean hasPrompt() {
                    return true;
                }

                @Override
                public void onConfirm(IntelUIAPI ui) {
                    CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
                    board.getState().deleteIntel(commodityId);
                }

                @Override
                public void onPrompt(TooltipMakerAPI tooltipMaker) {
                    tooltipMaker.addPara(L10n.get(CommodityL10n.DELETE_THIS_CONFIRMATION), Misc.getTextColor(), 0f);
                }
            }
        );
    }
}
