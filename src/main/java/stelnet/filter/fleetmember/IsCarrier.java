package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.market_old.dialog.DialogOption;

public class IsCarrier implements FleetMemberFilter {

    private final DialogOption option;

    public IsCarrier(DialogOption o) {
        option = o;
    }

    public boolean accept(FleetMemberAPI f) {
        switch (option) {
        case SHIP_CARRIER_NO:
            return !f.isCarrier();

        case SHIP_CARRIER_ONLY:
            return f.isCarrier();

        default:
            return true;
        }
    }
}
