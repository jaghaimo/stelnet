package stelnet.board.commodity.view;

import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.CommodityBoard;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Location;
import uilib.property.Size;

public class CommodityButton extends AreaCheckbox {

    private final String commodityId;

    public CommodityButton(CommoditySpecAPI commodity, boolean isOn) {
        super(new Size(190, UiConstants.DEFAULT_BUTTON_HEIGHT), commodity.getName(), true, isOn);
        commodityId = commodity.getId();
        setLocation(Location.TOP_RIGHT);
        setPadding(0);
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
        board.getRenderableState().setCommodityId(commodityId);
    }
}
