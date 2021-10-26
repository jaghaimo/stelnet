package stelnet.board.commodity.view.button;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.board.commodity.IntelTracker;
import stelnet.board.commodity.market.MarketApiWrapper;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.property.Location;
import uilib.property.Size;

public class IntelButton extends AreaCheckbox {

    public IntelButton(
        int i,
        final CommodityTab commodityTab,
        final String commodityId,
        final MarketApiWrapper market,
        final IntelTracker tracker
    ) {
        super(
            new Size(28f, 24f),
            String.valueOf(i),
            true,
            tracker.has(commodityTab.id, commodityId, market),
            Misc.getTextColor(),
            Misc.getGrayColor()
        );
        setLocation(Location.BOTTOM_LEFT);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    tracker.toggle(commodityId, commodityTab, market);
                }
            }
        );
    }
}
