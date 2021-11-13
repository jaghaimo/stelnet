package stelnet.board.query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import stelnet.util.IntelUtils;

public class QueryManager {

    private final Set<Query> queries = new HashSet<>();
    private final ResultMap resultMap = new ResultMap();

    public void addQuery(Query query) {
        queries.add(query);
        updateIntel(query);
    }

    public void deleteAll() {
        resultMap.clear();
        IntelUtils.removeAll(ResultIntel.class);
    }

    public void deleteQuery(Query query) {
        if (queries.contains(query)) {
            queries.remove(query);
            updateIntel();
        }
    }

    public void disableAll() {
        for (Query query : queries) {
            query.disable();
        }
        updateIntel();
    }

    public void enableAll() {
        for (Query query : queries) {
            query.enable();
        }
        updateIntel();
    }

    public int numberOfQueries() {
        return queries.size();
    }

    public void toggleAll() {
        for (Query query : queries) {
            query.toggle();
        }
        updateIntel();
    }

    private void updateIntel() {
        for (Query query : queries) {
            if (query.isEnabled()) {
                updateIntel(query);
            }
        }
    }

    private void updateIntel(Query query) {
        List<ResultSet> resultSets = query.execute();
        for (ResultSet resultSet : resultSets) {
            updateResult(resultSet);
        }
    }

    private void updateResult(ResultSet resultSet) {
        if (resultMap.containsKey(resultSet)) {
            resultMap.update(resultSet);
            return;
        }
        resultMap.add(resultSet);
        IntelUtils.add(new ResultIntel(this, resultSet));
    }
}
