package stelnet.commodity.view;

import java.awt.event.KeyEvent;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.IntelTracker;
import stelnet.helper.KeyboardHelper;
import stelnet.ui.Callable;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class IntelButton extends ToggleButton {

    public IntelButton(int i, final CommodityTab commodityTab, final String commodityId, final MarketAPI market,
            final IntelTracker tracker) {
        super(new Size(28f, 24f), String.valueOf(i), String.valueOf(i), true, Misc.getTextColor(), Misc.getGrayColor(),
                tracker.has(commodityTab.title, commodityId, market));
        setCallback(new Callable() {

            @Override
            public void callback() {
                tracker.toggle(commodityId, commodityTab, market);
                KeyboardHelper.send(KeyEvent.VK_E);
            }
        });
        setCutStyle(CutStyle.TL_BR);
    }
}
