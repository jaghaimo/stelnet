package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.IntelTracker;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

public class DoubleMarketButton extends AreaCheckbox {

    public DoubleMarketButton(
        int i,
        final String commodityId,
        final MarketAPI buyMarket,
        final MarketAPI sellMarket,
        final IntelTracker tracker
    ) {
        super(new Size(28f, 24f), String.valueOf(i), true, tracker.has(commodityId, buyMarket));
        setLocation(Location.BOTTOM_LEFT);
        setOffset(new Position(0, 1));
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    tracker.toggle(commodityId, buyMarket);
                    tracker.toggle(commodityId, sellMarket);
                }
            }
        );
    }
}
