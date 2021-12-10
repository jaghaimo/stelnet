package stelnet.board.commodity.view;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.CommodityBoard;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Location;
import uilib.property.Size;

public class CommodityButton extends AreaCheckbox {

    public CommodityButton(final CommoditySpecAPI commodity, boolean isOn) {
        super(new Size(190, UiConstants.DEFAULT_BUTTON_HEIGHT), commodity.getName(), true, isOn);
        setLocation(Location.TOP_RIGHT);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    String commodityId = commodity.getId();
                    CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
                    board.getRenderableState().setCommodityId(commodityId);
                }
            }
        );
    }
}
