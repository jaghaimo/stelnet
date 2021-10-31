package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMemberIsNotFrigate extends FleetMemberFilter {

    @Override
    protected boolean acceptFleetMember(FleetMemberAPI object) {
        return !object.isFrigate();
    }
}
