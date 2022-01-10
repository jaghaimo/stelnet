package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import uilib.RenderableComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public abstract class QueryProvider {

    public abstract List<?> getMatching(Set<Filter> filters);

    public abstract RenderableComponent getPreview(Set<Filter> filters, Size size);

    public List<ResultSet> getResults(Set<Filter> filters, GroupingStrategy groupingStrategy) {
        List<MarketAPI> markets = MarketProvider.getMarkets(true);
        CollectionUtils.reduce(markets, filters);
        List<ResultSet> resultSets = new LinkedList<>();
        processMarkets(resultSets, markets, filters, groupingStrategy);
        return resultSets;
    }

    protected abstract void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    );

    protected void addToResultSets(List<ResultSet> resultSets, ResultSet resultSet) {
        if (resultSet.size() > 0) {
            resultSets.add(resultSet);
        }
    }
}
