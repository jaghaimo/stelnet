package stelnet.board.commodity.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.CommodityAction;
import stelnet.board.commodity.CommodityBoard;
import stelnet.util.L10n;
import uilib.TabButton;

public class CommodityTabButton extends TabButton {

    private final CommodityAction currentTab;

    public CommodityTabButton(CommodityAction currentTab, CommodityAction activeTab, int shortcut) {
        super(L10n.get(currentTab), currentTab.equals(activeTab), shortcut);
        this.currentTab = currentTab;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        CommodityBoard board = CommodityBoard.getInstance(CommodityBoard.class);
        board.getRenderableState().setActiveTab(currentTab);
    }
}
