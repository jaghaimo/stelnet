package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.QueryManager;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import uilib.RenderableShowComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public abstract class QueryProvider {

    public abstract List<?> getMatching(Set<Filter> filters);

    public abstract RenderableShowComponent getPreview(Set<Filter> filters, Size size);

    public List<ResultSet> getResults(final Set<Filter> filters, final GroupingStrategy groupingStrategy) {
        final List<MarketAPI> markets = MarketProvider.getMarkets(true);
        CollectionUtils.reduce(markets, filters);
        final List<ResultSet> resultSets = new LinkedList<>();
        processMarkets(resultSets, markets, filters, groupingStrategy);
        return resultSets;
    }

    public Set<Filter> getAdditionalFilters(final QueryManager manager) {
        final Set<Filter> resultFilters = new LinkedHashSet<>();
        resultFilters.addAll(manager.getOtherFilters());
        return resultFilters;
    }

    protected abstract void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    );

    protected void addToResultSets(final List<ResultSet> resultSets, final ResultSet resultSet) {
        if (resultSet.size() > 0) {
            resultSets.add(resultSet);
        }
    }
}
