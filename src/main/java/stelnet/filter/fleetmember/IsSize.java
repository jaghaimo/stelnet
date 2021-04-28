package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.market.DialogOption;

public class IsSize implements FleetMemberFilter {

    private final DialogOption option;

    public IsSize(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
        case SHIP_SIZE_FRIGATE:
            return f.isFrigate();

        case SHIP_SIZE_DESTROYER:
            return f.isDestroyer();

        case SHIP_SIZE_CRUISER:
            return f.isCruiser();

        case SHIP_SIZE_CAPITAL:
            return f.isCapital();

        default:
            return false;
        }
    }
}
