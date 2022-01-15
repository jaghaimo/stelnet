package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.DModManager;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.util.L10n;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class FleetMemberHasDModCount extends ShipHullFilter {

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
        return L10n.get(CommonL10n.FILTER_NUMBER_OF_DMODS, numberOfDmods);
    }
}
