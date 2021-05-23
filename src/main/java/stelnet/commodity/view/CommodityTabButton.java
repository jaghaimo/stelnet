package stelnet.commodity.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.L10n;
import stelnet.commodity.CommodityBoard;
import stelnet.commodity.CommodityTab;
import stelnet.ui.EventHandler;
import stelnet.ui.TabButton;

public class CommodityTabButton extends TabButton {

    public CommodityTabButton(final CommodityTab currentTab, CommodityTab activeTab, int shortcut) {
        super(L10n.get("commodityTab" + currentTab.title), currentTab.equals(activeTab), shortcut);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                CommodityBoard board = CommodityBoard.getInstance();
                board.setActiveTab(currentTab);
            }
        });
    }
}
