package stelnet.filter;

import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullIsSize extends ShipHullFilter {

    private final ShipAPI.HullSize hullSize;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return hullSize.equals(shipHull.getHullSize());
    }
}
