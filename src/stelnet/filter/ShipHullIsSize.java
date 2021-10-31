package stelnet.filter;

import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullIsSize extends FleetMemberFilter {

    private final ShipAPI.HullSize hullSize;

    @Override
    public boolean accept(Object object) {
        if (object instanceof ShipHullIsSize) {
            return acceptShipHull((ShipHullSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptFleetMember(FleetMemberAPI fleetMember) {
        return accept(fleetMember.getHullSpec());
    }

    private boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return hullSize.equals(shipHull.getHullSize());
    }
}
