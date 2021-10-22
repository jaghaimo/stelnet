package stelnet.commodity.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.commodity.CommodityBoard;
import stelnet.commodity.CommodityTab;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class CommodityTabButton extends TabButton {

    public CommodityTabButton(
        final CommodityTab currentTab,
        CommodityTab activeTab,
        int shortcut
    ) {
        super(
            L10n.get("commodityTab" + currentTab.title),
            currentTab.equals(activeTab),
            shortcut
        );
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    CommodityBoard board = CommodityBoard.getInstance();
                    board.setActiveTab(currentTab);
                }
            }
        );
    }
}
