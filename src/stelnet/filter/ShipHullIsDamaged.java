package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;

public class ShipHullIsDamaged extends ShipHullFilter {

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return shipHull.isDHull();
    }
}
