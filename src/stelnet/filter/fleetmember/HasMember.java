package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class HasMember implements FleetMemberFilter {

    private final String shipHullSpecId;

    public HasMember(FleetMemberAPI f) {
        shipHullSpecId = f.getHullSpec().getHullId();
    }

    public boolean accept(FleetMemberAPI f) {
        String hullSpecId = f.getHullSpec().getHullId();
        return shipHullSpecId.equals(hullSpecId);
    }
}
