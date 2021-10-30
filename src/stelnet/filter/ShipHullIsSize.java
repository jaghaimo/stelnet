package stelnet.filter;

import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullIsSize extends Filter {

    private final ShipAPI.HullSize hullSize;

    @Override
    public boolean accept(FleetMemberAPI fleetMember) {
        return accept(fleetMember.getHullSpec());
    }

    @Override
    public boolean accept(ShipHullSpecAPI shipHull) {
        return hullSize.equals(shipHull.getHullSize());
    }
}
