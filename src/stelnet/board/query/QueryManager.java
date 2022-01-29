package stelnet.board.query;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.AnyHasId;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;
import stelnet.util.IntelUtils;
import stelnet.util.SectorUtils;

public class QueryManager {

    private int queryCounter = 0;

    @Getter
    private final Set<Filter> marketFilters = new LinkedHashSet<>();

    @Getter
    @Setter
    private GroupingStrategy groupingStrategy = GroupingStrategy.BY_MARKET;

    @Getter
    private final Set<Query> queries = new LinkedHashSet<>();

    private final ResultMap resultMap = new ResultMap();

    public QueryManager() {
        List<SubmarketSpecAPI> allSubmarkets = Global.getSettings().getAllSubmarketSpecs();
        CollectionUtils.reduce(allSubmarkets, Excluder.getQuerySubmarketFilter());
        for (SubmarketSpecAPI submarket : allSubmarkets) {
            marketFilters.add(new AnyHasId(submarket.getId()));
        }
    }

    public void addQuery(Query query) {
        if (!queries.contains(query)) {
            query.setNumber(++queryCounter);
            queries.add(query);
            updateIntel(query);
        }
    }

    public void deleteAll() {
        queries.clear();
        updateIntel();
    }

    public void deleteQuery(Query query) {
        if (queries.contains(query)) {
            queries.remove(query);
            updateIntel();
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

    public void updateIntel() {
        resultMap.clear();
        IntelUtils.removeAll(ResultIntel.class);
        SectorUtils.removeTransientScripts(ResultIntel.class);
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
