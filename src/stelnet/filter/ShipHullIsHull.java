package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullIsHull extends ShipHullFilter {

    private final ShipHullSpecAPI hull;

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        String hullId = hull.getHullId();
        if (shipHull.isDHull()) {
            return hullId.equals(shipHull.getBaseHullId()) || hullId.equals(shipHull.getDParentHullId());
        }
        return hullId.equals(shipHull.getHullId());
    }

    @Override
    public String toString() {
        return hull.getNameWithDesignationWithDashClass();
    }
}
