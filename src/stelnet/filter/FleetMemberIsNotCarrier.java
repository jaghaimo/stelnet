package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMemberIsNotCarrier extends FleetMemberFilter {

    @Override
    protected boolean acceptFleetMember(FleetMemberAPI object) {
        return !object.isCarrier();
    }
}
