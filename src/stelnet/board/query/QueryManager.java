package stelnet.board.query;

import java.util.LinkedList;
import java.util.List;
import lombok.experimental.Delegate;

public class QueryManager {

    @Delegate
    private final List<Query> queries = new LinkedList<>();

    public void disableAll() {
        for (Query query : queries) {
            query.disable();
        }
    }

    public void enableAll() {
        for (Query query : queries) {
            query.enable();
        }
    }

    public void toggleAll() {
        for (Query query : queries) {
            query.toggle();
        }
    }
}
