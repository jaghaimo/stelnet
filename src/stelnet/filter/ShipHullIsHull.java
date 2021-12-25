package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ShipHullIsHull extends ShipHullFilter {

    private final String hullId;
    private final String hullName;

    public ShipHullIsHull(ShipHullSpecAPI shipHullSpec) {
        this(shipHullSpec.getHullId(), shipHullSpec.getNameWithDesignationWithDashClass());
    }

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        if (shipHull.isDHull()) {
            return hullId.equals(shipHull.getBaseHullId()) || hullId.equals(shipHull.getDParentHullId());
        }
        return hullId.equals(shipHull.getHullId());
    }

    @Override
    public String toString() {
        return hullName;
    }
}
