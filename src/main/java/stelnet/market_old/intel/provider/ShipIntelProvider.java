package stelnet.market_old.intel.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.filter.submarket.HasFleetMember;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.market_old.intel.subject.IntelSubject;
import stelnet.market_old.intel.subject.ShipSubject;

public class ShipIntelProvider extends SubmarketProvider {

    private final FleetMemberAPI fleetMember;

    public ShipIntelProvider(FleetMemberAPI f) {
        fleetMember = f;
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new HasFleetMember(fleetMember);
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new ShipSubject(fleetMember, market);
    }
}
