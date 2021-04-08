package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class IsNotFrigate implements FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isFrigate();
    }
}
