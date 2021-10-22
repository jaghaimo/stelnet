package stelnet.filter.submarket;

import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class CanAcquireFleetMember implements SubmarketFilter {

    private final FleetMemberAPI fleetMember;

    public CanAcquireFleetMember(FleetMemberAPI fleetMember) {
        this.fleetMember = fleetMember;
    }

    public boolean accept(SubmarketAPI submarket) {
        return !submarket
            .getPlugin()
            .isIllegalOnSubmarket(fleetMember, TransferAction.PLAYER_BUY);
    }
}
