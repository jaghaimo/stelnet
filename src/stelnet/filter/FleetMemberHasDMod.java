package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FleetMemberHasDMod extends ShipHullFilter {

    private final String id;
    private final String name;

    @Override
    protected boolean acceptFleetMember(FleetMemberAPI fleetMember) {
        return fleetMember.getVariant().hasHullMod(id);
    }

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
