package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Set;
import stelnet.board.query.Query;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.QueryState;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.property.Size;

public class SearchButton extends Button {

    public SearchButton(final AddQueryFactory factory, Enum<?> translationId) {
        super(new Size(0, 30), L10n.get(translationId), true);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    QueryState state = QueryBoard.getInstance(QueryBoard.class).getState();
                    QueryProvider provider = factory.getProvider();
                    Set<Filter> filters = factory.getFilters();
                    Query query = new Query(provider, filters);
                    state.getQueryManger().addQuery(query);
                }
            }
        );
    }
}
