package stelnet.filter;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ShipHullIsSize extends ShipHullFilter {

    private final HullSize hullSize;

    @Override
    public String toString() {
        return L10n.common("HULL_" + hullSize);
    }

    protected boolean acceptShipHull(final ShipHullSpecAPI shipHull) {
        return hullSize.equals(shipHull.getHullSize());
    }
}
