package stelnet.board.query;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.util.IntelUtils;
import stelnet.util.SectorUtils;

public class QueryManager {

    @Getter
    private Query activeQuery;

    @Getter
    private final Set<Query> queries = new HashSet<>();

    private final ResultMap resultMap = new ResultMap();

    public void addQuery(Query query) {
        queries.add(query);
        setActiveQuery(null, query);
        updateIntel(query);
    }

    public void deleteAll() {
        activeQuery = null;
        queries.clear();
        updateIntel();
    }

    public void deleteQuery(Query query) {
        setActiveQuery(query, null);
        if (queries.contains(query)) {
            queries.remove(query);
            updateIntel();
        }
        Iterator<Query> iterator = queries.iterator();
        if (iterator.hasNext()) {
            setActiveQuery(null, iterator.next());
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

    public void selectQuery(Query newActiveQuery) {
        for (Query query : queries) {
            query.setSelected(false);
        }
        setActiveQuery(activeQuery, newActiveQuery);
    }

    public void toggleAll() {
        for (Query query : queries) {
            query.toggle();
        }
        updateIntel();
    }

    public void updateIntel() {
        resultMap.clear();
        IntelUtils.removeAll(ResultIntel.class);
        SectorUtils.removeScripts(ResultIntel.class);
        for (Query query : queries) {
            if (query.isEnabled()) {
                updateIntel(query);
            }
        }
    }

    private void setActiveQuery(Query checkForThisQuery, Query setToThisQuery) {
        if (activeQuery != checkForThisQuery) {
            return;
        }
        activeQuery = setToThisQuery;
        if (setToThisQuery != null) {
            setToThisQuery.setSelected(true);
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
        ResultIntel intel = new ResultIntel(this, resultSet);
        IntelUtils.add(intel);
    }
}
