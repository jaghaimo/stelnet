package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI.ShipTypeHints;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullHasHint extends ShipHullFilter {

    private final ShipTypeHints typeHint;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return shipHull.getHints().contains(typeHint);
    }
}
