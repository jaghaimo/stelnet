package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Set;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.Query;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.QueryState;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.EventHandler;
import uilib.property.Size;

public class FindMatchingButton extends C2Button {

    public FindMatchingButton(final QueryFactory factory) {
        super(new Size(0, 30), L10n.get(QueryL10n.FIND_MATCHING), true);
        overrideSize(30);
        setShortcut(Keyboard.KEY_M);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    QueryState state = QueryBoard.getInstance(QueryBoard.class).getState();
                    QueryManager manager = state.getQueryManager();
                    QueryProvider provider = factory.getProvider();
                    Set<Filter> filters = factory.getFilters();
                    Query query = new Query(manager, provider, filters);
                    manager.addQuery(query);
                    state.setActiveTab(QueryBoardTab.LIST);
                }
            }
        );
    }
}
