package stelnet.commodity.view;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.L10n;
import stelnet.commodity.CommodityBoard;
import stelnet.commodity.CommodityTab;
import stelnet.ui.Button;
import stelnet.ui.EventHandler;
import stelnet.ui.Size;

public class TabButton extends Button {

    public TabButton(final CommodityTab currentTab, CommodityTab activeTab, int shortcut) {
        super(new Size(140, 22), L10n.get("commodityTab" + currentTab.title), true, Misc.getGrayColor());
        if (currentTab.equals(activeTab)) {
            setColor(Misc.getButtonTextColor());
        }
        setCutStyle(CutStyle.TOP);
        setShortcut(shortcut);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                CommodityBoard board = CommodityBoard.getInstance();
                board.setActiveTab(currentTab);
            }
        });
    }
}
