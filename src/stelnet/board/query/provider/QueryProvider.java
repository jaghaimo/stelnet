package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.ResultSet;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import uilib.RenderableComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public abstract class QueryProvider {

    private final QueryFactory factory;

    public abstract List<?> getMatching(Set<Filter> filters);

    public List<ResultSet> getResults(Set<Filter> filters, boolean groupedBySystem) {
        List<MarketAPI> markets = MarketProvider.getMarkets(true);
        CollectionUtils.reduce(markets, filters);
        List<ResultSet> resultSets = new LinkedList<>();
        processMarkets(resultSets, markets, filters, groupedBySystem);
        return resultSets;
    }

    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return factory.getPreview(filters, size);
    }

    protected abstract void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final boolean groupedBySystem
    );

    protected void addToResultSets(List<ResultSet> resultSets, ResultSet resultSet) {
        if (resultSet.getResultNumber() > 0) {
            resultSets.add(resultSet);
        }
    }
}
