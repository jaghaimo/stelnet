package stelnet.commodity.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.L10n;
import stelnet.commodity.CommodityBoard;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Location;
import stelnet.ui.Size;

public class DeleteAllIntel extends Button {

    public DeleteAllIntel() {
        super(new Size(200, 24), L10n.get("commodityDeleteAll"), true, Misc.getNegativeHighlightColor());
        setLocation(Location.BOTTOM_RIGHT);
        setHandler(new EventHandler() {

            @Override
            public boolean hasPrompt() {
                return true;
            }

            @Override
            public void onConfirm(IntelUIAPI ui) {
                CommodityBoard.getInstance().deleteIntel();
            }

            @Override
            public void onPrompt(TooltipMakerAPI tooltipMaker) {
                tooltipMaker.addPara(L10n.get("commodityDeleteAllConfirmation"), Misc.getTextColor(), 0f);
            }
        });
    }
}
