package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipBaseHullId extends ShipHullFilter {

    private final String id;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return id.equalsIgnoreCase(shipHull.getBaseHullId());
    }
}
