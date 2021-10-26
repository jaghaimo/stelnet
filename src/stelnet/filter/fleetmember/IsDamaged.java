package stelnet.filter.fleetmember;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class IsDamaged implements FleetMemberFilter {

    private final Boolean isDamaged;

    public IsDamaged(Boolean isDamaged) {
        this.isDamaged = isDamaged;
    }

    public boolean accept(FleetMemberAPI f) {
        if (isDamaged == null) {
            return true;
        }
        return isDamaged.equals(f.getHullSpec().isDHull());
    }
}
