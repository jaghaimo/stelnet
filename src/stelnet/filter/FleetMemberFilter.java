package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public abstract class FleetMemberFilter extends MarketFilter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof FleetMemberAPI) {
            return acceptFleetMember((FleetMemberAPI) object);
        }
        return super.accept(object);
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
            if (acceptSubmarket(submarket)) {
                return true;
            }
        }
        return false;
    }

    protected boolean acceptSubmarket(SubmarketAPI submarket) {
        for (FleetMemberAPI fleetMember : submarket.getCargo().getMothballedShips().getMembersListCopy()) {
            if (acceptFleetMember(fleetMember)) {
                return true;
            }
        }
        return false;
    }

    protected abstract boolean acceptFleetMember(FleetMemberAPI fleetMember);
}
