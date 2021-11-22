package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullNoBuiltIns extends ShipHullFilter {

    private final String name;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return shipHull.getBuiltInMods().isEmpty();
    }

    @Override
    public String toString() {
        return name;
    }
}
