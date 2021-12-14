package stelnet.board.commodity.view;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.CommodityAction;
import stelnet.board.commodity.IntelTracker;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class IntelButton extends AreaCheckbox {

    public IntelButton(
        int i,
        final CommodityAction commodityAction,
        final String commodityId,
        final MarketAPI market,
        final IntelTracker tracker
    ) {
        super(new Size(28f, 24f), String.valueOf(i), true, tracker.has(commodityAction.name(), commodityId, market));
        setLocation(Location.BOTTOM_LEFT);
        setOffset(new Position(0, 1));
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    tracker.toggle(commodityId, commodityAction, market);
                }
            }
        );
    }
}
