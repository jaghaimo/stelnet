package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class IsNotDestroyer implements FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isDestroyer();
    }
}
