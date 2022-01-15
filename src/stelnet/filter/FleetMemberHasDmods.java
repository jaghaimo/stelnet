package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.DModManager;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class FleetMemberHasDmods extends ShipHullFilter {

    private final int numberOfDmods;

    @Override
    protected boolean acceptFleetMember(FleetMemberAPI fleetMember) {
        return DModManager.getNumDMods(fleetMember.getVariant()) == numberOfDmods;
    }

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(numberOfDmods) + " d-mods";
    }
}
