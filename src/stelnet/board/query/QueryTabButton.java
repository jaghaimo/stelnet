package stelnet.board.query;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class QueryTabButton extends TabButton {

    public QueryTabButton(final QueryBoardTab currentTab, QueryBoardTab activeTab, int shortcut) {
        super(L10n.get(currentTab), currentTab.equals(activeTab), shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    QueryBoard board = QueryBoard.getInstance(QueryBoard.class);
                    board.getState().setActiveTab(currentTab);
                }
            }
        );
    }
}
