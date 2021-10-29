package stelnet.filter;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public abstract class FleetMemberFilter extends Filter {

    @Override
    public abstract boolean accept(FleetMemberAPI cargoStack);
}
