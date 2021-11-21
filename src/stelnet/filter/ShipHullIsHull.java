package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullIsHull extends ShipHullFilter {

    private final ShipHullSpecAPI hull;

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        if (shipHull.isDHull()) {
            return shipHull.getBaseHullId().equals(hull.getHullId());
        }
        return shipHull.getHullId().equals(hull.getHullId());
    }

    @Override
    public String toString() {
        return hull.getNameWithDesignationWithDashClass();
    }
}
