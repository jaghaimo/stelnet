package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class IsNotCarrier implements FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isCarrier();
    }
}
