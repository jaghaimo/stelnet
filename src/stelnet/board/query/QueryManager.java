package stelnet.board.query;

import java.util.LinkedList;
import java.util.List;
import lombok.experimental.Delegate;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalAnd;

public class QueryManager {

    @Delegate
    private final List<IntelQuery> queries = new LinkedList<>();

    public void add(QueryProvider queryProvider, List<Filter> filters) {
        IntelQuery query = new IntelQuery(queryProvider, new LogicalAnd(filters));
        query.create();
        add(query);
    }

    public void disableAll() {
        for (IntelQuery query : queries) {
            query.disable();
        }
    }

    public void enableAll() {
        for (IntelQuery query : queries) {
            query.enable();
        }
    }

    public void refreshAll() {
        for (IntelQuery query : queries) {
            query.refresh();
        }
    }

    public void toggleAll() {
        for (IntelQuery query : queries) {
            query.toggle();
        }
    }
}
