package stelnet.commodity.element;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard;
import stelnet.commodity.ui.Callable;
import stelnet.commodity.ui.Size;
import stelnet.commodity.ui.ToggleButton;

public class CommodityButton extends ToggleButton {

    public CommodityButton(final CommoditySpecAPI commodity, boolean isOn) {
        super(new Size(200, 24), commodity.getName(), commodity.getName(), true, Misc.getHighlightColor(),
                Misc.getGrayColor(), isOn);

        setCallback(new Callable() {

            @Override
            public void callback() {
                String commodityId = commodity.getId();
                CommodityBoard board = CommodityBoard.getInstance();
                board.setActiveId(commodityId);
            }
        });
    }
}
