package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Set;
import stelnet.board.query.Query;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.QueryManager;
import stelnet.board.query.QueryState;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.EventHandler;
import uilib.property.Size;

public class SearchButton extends C2Button {

    public SearchButton(final AddQueryFactory factory, Enum<?> translationId, boolean isEnabled) {
        super(new Size(0, 30), L10n.get(translationId), isEnabled);
        overrideSize(20);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    QueryState state = QueryBoard.getInstance(QueryBoard.class).getState();
                    QueryManager manager = state.getQueryManger();
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
