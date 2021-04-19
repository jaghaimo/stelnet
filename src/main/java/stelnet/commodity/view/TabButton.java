package stelnet.commodity.view;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.commodity.CommodityBoard;
import stelnet.commodity.CommodityBoard.CommodityTab;
import stelnet.ui.Button;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;

public class TabButton extends Button {

    public TabButton(final CommodityTab currentTab, CommodityTab activeTab, int shortcut) {
        super(new Size(200, 22), currentTab.title, true, Misc.getGrayColor());
        if (currentTab.equals(activeTab)) {
            setColor(Misc.getButtonTextColor());
        }
        setCutStyle(CutStyle.TOP);
        setShortcut(shortcut);
        setCallback(new SimpleCallback() {

            @Override
            public void confirm(IntelUIAPI ui) {
                CommodityBoard board = CommodityBoard.getInstance();
                board.setActiveTab(currentTab);
            }
        });
    }
}
