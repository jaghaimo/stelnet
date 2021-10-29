package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMemberIsNotCarrier extends FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isCarrier();
    }
}
