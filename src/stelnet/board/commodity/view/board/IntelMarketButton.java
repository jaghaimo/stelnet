package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.IntelTracker;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class IntelMarketButton extends AreaCheckbox {

    public IntelMarketButton(int i, final String commodityId, final MarketAPI market, final IntelTracker tracker) {
        super(new Size(28f, 24f), String.valueOf(i), true, tracker.has(commodityId, market));
        setLocation(Location.BOTTOM_LEFT);
        setOffset(new Position(0, 1));
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    tracker.toggle(commodityId, market);
                }
            }
        );
    }
}
