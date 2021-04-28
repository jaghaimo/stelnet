package stelnet.commodity.view;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard;
import stelnet.ui.AbstractButtonHandler;
import stelnet.ui.Location;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class CommodityButton extends ToggleButton {

    public CommodityButton(final CommoditySpecAPI commodity, boolean isOn) {
        super(new Size(200, 24), commodity.getName(), commodity.getName(), true, Misc.getHighlightColor(),
                Misc.getGrayColor(), isOn);
        setLocation(Location.TOP_RIGHT);
        setHandler(new AbstractButtonHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                String commodityId = commodity.getId();
                CommodityBoard board = CommodityBoard.getInstance();
                board.setActiveId(commodityId);
            }
        });
    }
}
