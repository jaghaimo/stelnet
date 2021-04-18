package stelnet.commodity.view;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard;
import stelnet.ui.Callable;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class CommodityButton extends ToggleButton {

    public CommodityButton(final CommoditySpecAPI commodity, boolean isOn) {
        super(new Size(200, 24), commodity.getName(), commodity.getName(), true, Misc.getHighlightColor(),
                Misc.getGrayColor(), isOn);

        setCallback(new Callable() {

            @Override
            public void confirm(IntelUIAPI ui) {
                String commodityId = commodity.getId();
                CommodityBoard board = CommodityBoard.getInstance();
                board.setActiveId(commodityId);
            }

            @Override
            public void cancel() {
            }

            @Override
            public void prompt(TooltipMakerAPI tooltipMaker) {
            }
        });
    }
}
