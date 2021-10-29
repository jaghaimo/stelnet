package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMemberIsNotWarship extends FleetMemberFilter {

    @Override
    public boolean accept(FleetMemberAPI object) {
        return object.isCarrier() || object.isCivilian();
    }
}
