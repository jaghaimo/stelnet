package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.L10n;
import stelnet.market.QueryBoard;
import stelnet.market.QueryTab;
import stelnet.ui.EventHandler;
import stelnet.ui.TabButton;

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