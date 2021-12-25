package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ShipHullNoBuiltIns extends ShipHullFilter {

    private final String name;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return shipHull.getBuiltInMods().isEmpty();
    }

    @Override
    public String toString() {
        return name;
    }
}
