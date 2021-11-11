package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.board.query.ResultSet;
import stelnet.filter.AnyShowInCodex;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.ShipBaseHullId;
import stelnet.filter.ShipHullIsDamaged;
import stelnet.filter.ShipHullIsSize;
import stelnet.util.CollectionUtils;
import stelnet.util.MarketUtils;

public abstract class QueryProvider {

    public abstract List<?> getMatching(Set<Filter> filters);

    public List<ResultSet> getResults(Set<Filter> filters) {
        List<MarketAPI> markets = MarketUtils.getMarkets(true);
        CollectionUtils.reduce(markets, filters);
        List<ResultSet> resultSets = new LinkedList<>();
        processMarkets(resultSets, markets, filters);
        return resultSets;
    }

    protected abstract void processMarkets(List<ResultSet> resultSets, List<MarketAPI> markets, Set<Filter> filters);

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
