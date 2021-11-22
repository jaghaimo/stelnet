package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FleetMemberHasDmod extends ShipHullFilter {

    private final HullModSpecAPI dmod;

    @Override
    protected boolean acceptFleetMember(FleetMemberAPI fleetMember) {
        return fleetMember.getVariant().hasHullMod(dmod.getId());
    }

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return true;
    }

    @Override
    public String toString() {
        return dmod.getDisplayName();
    }
}
