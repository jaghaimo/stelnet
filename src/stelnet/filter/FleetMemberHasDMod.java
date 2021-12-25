package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class FleetMemberHasDMod extends ShipHullFilter {

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
