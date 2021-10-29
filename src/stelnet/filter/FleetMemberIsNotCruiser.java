package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMemberIsNotCruiser extends FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return !object.isCruiser();
    }
}
