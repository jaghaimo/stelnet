package stelnet.board.commodity.view.button;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.commodity.CommodityBoard;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Size;

public class DeleteCommodityIntel extends Button {

    public DeleteCommodityIntel(final String commodityId) {
        super(new Size(200, 24), L10n.get("commodityDeleteThis"), true, Misc.getButtonTextColor());
        setLocation(Location.BOTTOM_RIGHT);
        setHandler(
            new EventHandler() {
                @Override
                public boolean hasPrompt() {
                    return true;
                }

                @Override
                public void onConfirm(IntelUIAPI ui) {
                    CommodityBoard board = CommodityBoard.getInstance();
                    board.getState().deleteIntel(commodityId);
                }

                @Override
                public void onPrompt(TooltipMakerAPI tooltipMaker) {
                    tooltipMaker.addPara(L10n.get("commodityDeleteThisConfirmation"), Misc.getTextColor(), 0f);
                }
            }
        );
    }
}
