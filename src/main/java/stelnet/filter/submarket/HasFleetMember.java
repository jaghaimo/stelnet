package stelnet.filter.submarket;

import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.filter.fleetmember.HasMember;
import stelnet.util.CollectionReducer;

public class HasFleetMember implements SubmarketFilter {

    private final FleetMemberFilter filter;

    public HasFleetMember(FleetMemberAPI fleetMember) {
        filter = new HasMember(fleetMember);
    }

    public boolean accept(SubmarketAPI submarket) {
        List<FleetMemberAPI> fleetMembers = submarket
            .getCargo()
            .getMothballedShips()
            .getMembersListCopy();
        CollectionReducer.reduce(fleetMembers, filter);
        return !fleetMembers.isEmpty();
    }
}
