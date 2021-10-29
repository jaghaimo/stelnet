package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMemberIsNotFrigate extends FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isFrigate();
    }
}
