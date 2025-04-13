package stelnet.board.query;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.filter.ResultHasId;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;
import stelnet.util.StelnetHelper;

public class QueryManager {

    private int queryCounter = 0;

    @Getter
    private final Set<Filter> dModCountFilters = new LinkedHashSet<>();

    @Getter
    private final Set<Filter> dModTypesFilters = new LinkedHashSet<>();

    @Getter
    private final Set<Filter> otherFilters = new LinkedHashSet<>();

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

    public Set<Filter> getSubmarketFilters() {
        if (Excluder.submarketFilters == null) {
            Set<Filter> submarketFilters = new LinkedHashSet<>();
            List<SubmarketSpecAPI> allSubmarketSpecs = getSubmarketSpecs();
            for (SubmarketSpecAPI submarketSpec : allSubmarketSpecs) {
                submarketFilters.add(getSubmarketFilter(submarketSpec));
            }
            Excluder.submarketFilters = submarketFilters;
        }
        return Excluder.submarketFilters;
    }

    public Filter getSubmarketFilter(SubmarketSpecAPI submarketSpec) {
        return new ResultHasId(submarketSpec.getId());
    }

    public List<SubmarketSpecAPI> getSubmarketSpecs() {
        List<SubmarketSpecAPI> allSubmarketSpecs = Global.getSettings().getAllSubmarketSpecs();
        CollectionUtils.reduce(allSubmarketSpecs, Excluder.getSubmarketFilter());
        return allSubmarketSpecs;
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
        StelnetHelper.removeIntel(ResultIntel.class);
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
