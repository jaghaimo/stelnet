package stelnet.board.query;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.EventHandler;
import uilib.TabButton;

public class QueryTabButton extends TabButton {

    public QueryTabButton(final QueryBoardTab currentTab, final QueryBoardTab activeTab, final int shortcut) {
        super(L10n.query("STATE_" + currentTab.name()), currentTab.equals(activeTab), shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    final QueryBoard board = StelnetHelper.getInstance(QueryBoard.class);
                    board.getState().setActiveTab(currentTab);
                }
            }
        );
    }
}
