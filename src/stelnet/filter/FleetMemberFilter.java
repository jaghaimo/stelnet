package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public abstract class FleetMemberFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof FleetMemberAPI) {
            return acceptFleetMember((FleetMemberAPI) object);
        }
        return super.accept(object);
    }

    protected abstract boolean acceptFleetMember(FleetMemberAPI fleetMember);
}
