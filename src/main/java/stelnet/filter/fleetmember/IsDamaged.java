package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.market.DialogOption;

public class IsDamaged implements FleetMemberFilter {

    private final DialogOption option;

    public IsDamaged(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
        case SHIP_DAMAGED_NO:
            return !f.getHullSpec().isDHull();

        case SHIP_DAMAGED_ONLY:
            return f.getHullSpec().isDHull();

        default:
            return true;
        }
    }
}
