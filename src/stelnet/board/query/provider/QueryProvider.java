package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.ResultSet;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.filter.AnyShowInCodex;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.ShipBaseHullId;
import stelnet.filter.ShipHullIsDamaged;
import stelnet.filter.ShipHullIsSize;
import stelnet.util.CollectionUtils;
import stelnet.util.MarketUtils;
import uilib.RenderableComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public abstract class QueryProvider {

    private final QueryFactory factory;

    public abstract List<?> getMatching(Set<Filter> filters);

    public List<ResultSet> getResults(Set<Filter> filters) {
        List<MarketAPI> markets = MarketUtils.getMarkets(true);
        CollectionUtils.reduce(markets, filters);
        List<ResultSet> resultSets = new LinkedList<>();
        processMarkets(resultSets, markets, filters);
        return resultSets;
    }

    public RenderableComponent getPreview(Size size) {
        return factory.getPreview(size);
    }

    protected abstract void processMarkets(List<ResultSet> resultSets, List<MarketAPI> markets, Set<Filter> filters);

    protected void addToResultSets(List<ResultSet> resultSets, ResultSet resultSet) {
        if (resultSet.getResultNumber() > 0) {
            resultSets.add(resultSet);
        }
    }

    protected void filter(List<?> elements) {
        CollectionUtils.reduce(elements, getCommonFilters());
    }

    private List<Filter> getCommonFilters() {
        return Arrays.asList(
            new AnyShowInCodex(),
            new LogicalNot(new ShipHullIsSize(ShipAPI.HullSize.FIGHTER)),
            new LogicalNot(new ShipHullIsDamaged()),
            new LogicalNot(new ShipBaseHullId("gargoyle")),
            new LogicalNot(new ShipBaseHullId("merlon")),
            new LogicalNot(new ShipBaseHullId("ravelin")),
            new LogicalNot(new ShipBaseHullId("shuttlepod"))
        );
    }
}
