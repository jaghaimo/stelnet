package stelnet.commodity.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Location;
import stelnet.ui.Size;

public class DeleteCommodityIntel extends Button {

    public DeleteCommodityIntel(final String commodityId) {
        super(new Size(200, 24), "Delete This", true, Misc.getButtonTextColor());
        setLocation(Location.BOTTOM_RIGHT);
        setHandler(new EventHandler() {

            @Override
            public boolean hasPrompt() {
                return true;
            }

            @Override
            public void onConfirm(IntelUIAPI ui) {
                CommodityBoard.getInstance().deleteIntel(commodityId);
            }

            @Override
            public void onPrompt(TooltipMakerAPI tooltipMaker) {
                tooltipMaker.addPara("Are you sure you want to delete all intel for this commodity?",
                        Misc.getTextColor(), 0f);
            }
        });
    }
}