package stelnet.board.commodity.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityIntel;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class DeleteIntel extends Button {

    public DeleteIntel(float width, final CommodityIntel intel) {
        super(new Size(width, 24), L10n.get("commodityDelete"), true, Misc.getButtonTextColor());
        setHandler(
            new EventHandler() {
                @Override
                public boolean hasPrompt() {
                    return true;
                }

                @Override
                public void onConfirm(IntelUIAPI ui) {
                    CommodityBoard board = CommodityBoard.getInstance();
                    board.getState().deleteIntel(intel);
                }

                @Override
                public void onPrompt(TooltipMakerAPI tooltipMaker) {
                    tooltipMaker.addPara(L10n.get("commodityDeleteConfirmation"), Misc.getTextColor(), 0f);
                }
            }
        );
    }
}
