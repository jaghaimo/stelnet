package stelnet.board.query;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.filter.ResultHasId;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
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

    public void addQuery(final Query query) {
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

    public void deleteQuery(final Query query) {
        if (queries.contains(query)) {
            queries.remove(query);
            updateIntel();
        }
    }

    public Set<Filter> getSubmarketFilters() {
        final Set<Filter> submarketFilters = new LinkedHashSet<>();
        final List<SubmarketSpecAPI> allSubmarketSpecs = getSubmarketSpecs();
        for (final SubmarketSpecAPI submarketSpec : allSubmarketSpecs) {
            submarketFilters.add(getSubmarketFilter(submarketSpec));
        }
        return submarketFilters;
    }

    public Filter getSubmarketFilter(final SubmarketSpecAPI submarketSpec) {
        return new ResultHasId(submarketSpec.getId());
    }

    public List<SubmarketSpecAPI> getSubmarketSpecs() {
        final List<SubmarketSpecAPI> allSubmarketSpecs = Global.getSettings().getAllSubmarketSpecs();
        CollectionUtils.reduce(allSubmarketSpecs, Excluder.getSubmarketFilter());
        return allSubmarketSpecs;
    }

    public void setAllEnabled(final boolean isEnabled) {
        for (final Query query : queries) {
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
        for (final Query query : queries) {
            if (query.isEnabled()) {
                updateIntel(query);
            }
        }
    }

    private void updateIntel(final Query query) {
        final List<ResultSet> resultSets = query.execute(groupingStrategy);
        for (final ResultSet resultSet : resultSets) {
            updateResult(resultSet);
        }
    }

    private void updateResult(final ResultSet resultSet) {
        final boolean hasGrouping = resultSet.getKey() != null;
        final boolean hasIntel = resultMap.containsKey(resultSet);
        final boolean needsIntel = !hasGrouping || !hasIntel;
        if (hasIntel) {
            resultMap.update(resultSet);
        } else {
            resultMap.add(resultSet);
        }
        if (needsIntel) {
            final ResultIntel intel = new ResultIntel(this, resultSet);
            Global.getSector().getIntelManager().addIntel(intel, true);
        }
    }
}
