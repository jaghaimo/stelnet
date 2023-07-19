package stelnet.board.commodity.view.board;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.commodity.CommodityAction;
import stelnet.board.commodity.CommodityBoard;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.TabButton;

public class TabViewButton extends TabButton {

    private final CommodityAction currentTab;

    public TabViewButton(final CommodityAction currentTab, final CommodityAction activeTab, final int shortcut) {
        super(L10n.commodity(currentTab.key()), currentTab.equals(activeTab), shortcut);
        this.currentTab = currentTab;
    }

    @Override
    public void onConfirm(final IntelUIAPI ui) {
        final CommodityBoard board = StelnetHelper.getInstance(CommodityBoard.class);
        board.getRenderableState().setActiveTab(currentTab);
    }
}
