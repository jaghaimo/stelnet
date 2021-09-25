package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class IsCarrier implements FleetMemberFilter {

    private final Boolean isCarrier;

    public IsCarrier(Boolean isCarrier) {
        this.isCarrier = isCarrier;
    }

    public boolean accept(FleetMemberAPI f) {
        if (isCarrier == null) {
            return true;
        }
        return isCarrier.equals(f.isCarrier());
    }
}
