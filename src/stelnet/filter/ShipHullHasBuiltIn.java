package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullHasBuiltIn extends ShipHullFilter {

    private final String id;
    private final String name;

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return shipHull.getBuiltInMods().contains(id);
    }

    @Override
    public String toString() {
        return name;
    }
}
