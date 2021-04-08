package stelnet.commodity.element;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard;
import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.commodity.ui.Button;
import stelnet.commodity.ui.Callable;
import stelnet.commodity.ui.Size;

public class TabButton extends Button {

    public TabButton(final CommodityTab currentTab, CommodityTab activeTab, int shortcut) {
        super(new Size(200, 22), currentTab.title, true, Misc.getGrayColor());
        if (currentTab.equals(activeTab)) {
            setColor(Misc.getButtonTextColor());
        }
        setCallback(new Callable() {

            @Override
            public void callback() {
                CommodityBoard board = CommodityBoard.getInstance();
                board.setActiveTab(currentTab);
            }
        });
        setCutStyle(CutStyle.TOP);
        setShortcut(shortcut);
    }
}
