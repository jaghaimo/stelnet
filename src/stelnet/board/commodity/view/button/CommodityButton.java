package stelnet.board.commodity.view.button;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.commodity.CommodityBoard;
import uilib.EventHandler;
import uilib.ToggleButton;
import uilib.property.Location;
import uilib.property.Size;

public class CommodityButton extends ToggleButton {

    public CommodityButton(final CommoditySpecAPI commodity, boolean isOn) {
        super(
            new Size(190, 24),
            commodity.getName(),
            commodity.getName(),
            true,
            Misc.getHighlightColor(),
            Misc.getGrayColor(),
            isOn
        );
        setLocation(Location.TOP_RIGHT);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    String commodityId = commodity.getId();
                    CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
                    board.getState().setCommodityId(commodityId);
                }
            }
        );
    }
}
