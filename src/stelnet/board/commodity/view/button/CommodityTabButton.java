package stelnet.board.commodity.view.button;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.CommodityBoard;
import stelnet.board.commodity.CommodityState.CommodityTab;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class CommodityTabButton extends TabButton {

    public CommodityTabButton(final CommodityTab currentTab, CommodityTab activeTab, int shortcut) {
        super(L10n.get("commodityTab" + currentTab.id), currentTab.equals(activeTab), shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
                    board.getState().setActiveTab(currentTab);
                }
            }
        );
    }
}
