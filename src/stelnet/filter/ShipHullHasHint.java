package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI.ShipTypeHints;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ShipHullHasHint extends ShipHullFilter {

    private final ShipTypeHints typeHint;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return shipHull.getHints().contains(typeHint);
    }
}
