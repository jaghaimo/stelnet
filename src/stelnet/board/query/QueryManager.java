package stelnet.board.query;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.util.IntelUtils;
import stelnet.util.SectorUtils;

public class QueryManager {

    private int queryCounter = 0;

    @Getter
    private Query activeQuery;

    @Getter
    @Setter
    private GroupingStrategy groupingStrategy = GroupingStrategy.BY_MARKET;

    @Getter
    private final Set<Query> queries = new LinkedHashSet<>();

    private final ResultMap resultMap = new ResultMap();

    public void addQuery(Query query) {
        if (!queries.contains(query)) {
            query.setNumber(++queryCounter);
            queries.add(query);
            setActiveQuery(null, query);
            updateIntel(query);
        }
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

    public void setAllEnabled(boolean isEnabled) {
        for (Query query : queries) {
            query.setEnabled(isEnabled);
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

    public void updateIntel() {
        resultMap.clear();
        IntelUtils.removeAll(SingleResultIntel.class);
        IntelUtils.removeAll(MultiResultIntel.class);
        SectorUtils.removeTransientScripts(MultiResultIntel.class);
        for (Query query : queries) {
            if (query.isEnabled()) {
                updateIntel(query);
            }
        }
    }

    private void updateIntel(Query query) {
        List<ResultSet> resultSets = query.execute(groupingStrategy);
        for (ResultSet resultSet : resultSets) {
            updateResult(resultSet);
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

    private void updateResult(ResultSet resultSet) {
        boolean hasGrouping = resultSet.getKey() != null;
        boolean hasIntel = resultMap.containsKey(resultSet);
        boolean needsIntel = !hasGrouping || !hasIntel;
        if (hasIntel) {
            resultMap.update(resultSet);
        } else {
            resultMap.add(resultSet);
        }
        if (needsIntel) {
            groupingStrategy.createIntel(this, resultSet);
        }
    }
}
