package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.market.QueryBoard;
import stelnet.market.QueryTab;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class QueryTabButton extends TabButton {

    public QueryTabButton(final QueryTab currentTab, QueryTab activeTab, int shortcut) {
        super(L10n.get("marketQueryTab" + currentTab.title), currentTab.equals(activeTab), shortcut);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                QueryBoard board = QueryBoard.getInstance();
                board.setActiveTab(currentTab);
            }
        });
    }
}