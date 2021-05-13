package stelnet.commodity.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityTab;
import stelnet.commodity.IntelTracker;
import stelnet.commodity.market.MarketApiWrapper;
import stelnet.ui.AreaCheckbox;
import stelnet.ui.EventHandler;
import stelnet.ui.Location;
import stelnet.ui.Size;

public class IntelButton extends AreaCheckbox {

    // @formatter:off
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
                tracker.has(commodityTab.getTitle(), commodityId, market),
                Misc.getTextColor(),
                Misc.getGrayColor()
        );
        // @formatter:on
        setLocation(Location.BOTTOM_LEFT);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                tracker.toggle(commodityId, commodityTab, market);
            }
        });
    }
}
