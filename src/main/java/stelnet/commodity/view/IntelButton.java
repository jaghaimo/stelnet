package stelnet.commodity.view;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.IntelTracker;
import stelnet.ui.AreaCheckbox;
import stelnet.ui.EventHandler;
import stelnet.ui.Location;
import stelnet.ui.Size;

public class IntelButton extends AreaCheckbox {

    public IntelButton(int i, final CommodityTab commodityTab, final String commodityId, final MarketAPI market,
            final IntelTracker tracker) {
        super(new Size(28f, 24f), String.valueOf(i), true, tracker.has(commodityTab.title, commodityId, market),
                Misc.getTextColor(), Misc.getGrayColor());
        setLocation(Location.BOTTOM_LEFT);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                tracker.toggle(commodityId, commodityTab, market);
            }
        });
    }
}
