package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipAPI;
import java.util.Arrays;
import java.util.List;
import stelnet.board.query.ResultSystemMap;
import stelnet.filter.AnyShowInCodex;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.ShipBaseHullId;
import stelnet.filter.ShipHullIsDamaged;
import stelnet.filter.ShipHullIsSize;
import stelnet.util.CollectionUtils;

public abstract class QueryProvider {

    public abstract List<?> getMatching(List<Filter> filters);

    public abstract List<ResultSystemMap> getResults(List<Filter> filters);

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
