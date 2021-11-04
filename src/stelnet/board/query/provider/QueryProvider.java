package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import java.util.Arrays;
import java.util.List;
import stelnet.filter.AnyShowInCodexFilter;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNotFilter;
import stelnet.filter.ShipBaseHullId;
import stelnet.filter.ShipHullIsSize;
import stelnet.util.CollectionUtils;
import stelnet.util.SettingsUtils;

public class QueryProvider {

    protected <T> void filter(List<T> elements) {
        CollectionUtils.reduce(elements, getCommonFilters());
    }

    protected List<ShipHullSpecAPI> getShipHulls() {
        List<ShipHullSpecAPI> allShipHullSpecs = SettingsUtils.getAllShipHullSpecs();
        filter(allShipHullSpecs);
        return allShipHullSpecs;
    }

    private List<Filter> getCommonFilters() {
        return Arrays.asList(
            new AnyShowInCodexFilter(),
            new LogicalNotFilter(new ShipHullIsSize(ShipAPI.HullSize.FIGHTER)),
            new LogicalNotFilter(new ShipBaseHullId("gargoyle")),
            new LogicalNotFilter(new ShipBaseHullId("merlon")),
            new LogicalNotFilter(new ShipBaseHullId("ravelin"))
        );
    }
}
