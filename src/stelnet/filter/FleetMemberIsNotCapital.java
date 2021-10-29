package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMemberIsNotCapital extends FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isCapital();
    }
}
