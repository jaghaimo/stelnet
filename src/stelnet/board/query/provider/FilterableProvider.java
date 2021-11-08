package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipAPI;
import java.util.Arrays;
import java.util.List;
import stelnet.filter.AnyShowInCodexFilter;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNotFilter;
import stelnet.filter.ShipBaseHullId;
import stelnet.filter.ShipHullIsDamaged;
import stelnet.filter.ShipHullIsSize;
import stelnet.util.CollectionUtils;

public abstract class FilterableProvider {

    protected <T> void filter(List<T> elements) {
        CollectionUtils.reduce(elements, getCommonFilters());
    }

    private List<Filter> getCommonFilters() {
        return Arrays.asList(
            new AnyShowInCodexFilter(),
            new LogicalNotFilter(new ShipHullIsSize(ShipAPI.HullSize.FIGHTER)),
            new LogicalNotFilter(new ShipHullIsDamaged()),
            new LogicalNotFilter(new ShipBaseHullId("gargoyle")),
            new LogicalNotFilter(new ShipBaseHullId("merlon")),
            new LogicalNotFilter(new ShipBaseHullId("ravelin")),
            new LogicalNotFilter(new ShipBaseHullId("shuttlepod"))
        );
    }
}
