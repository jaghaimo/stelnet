package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FleetMemberPristine extends ShipHullFilter {

    private final String type;

    @Override
    protected boolean acceptFleetMember(FleetMemberAPI fleetMember) {
        return !fleetMember.getHullSpec().isDHull();
    }

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return true;
    }

    @Override
    public String toString() {
        return type;
    }
}
