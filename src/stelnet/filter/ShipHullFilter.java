package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public abstract class ShipHullFilter extends FleetMemberFilter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof ShipHullSpecAPI) {
            return acceptShipHull((ShipHullSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptFleetMember(FleetMemberAPI fleetMember) {
        return accept(fleetMember.getHullSpec());
    }

    protected abstract boolean acceptShipHull(ShipHullSpecAPI shipHull);
}
