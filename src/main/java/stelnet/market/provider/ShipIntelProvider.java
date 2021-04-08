package stelnet.market.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.filter.submarket.HasFleetMember;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.market.IntelSubject;
import stelnet.market.subject.ShipSubject;

public class ShipIntelProvider extends SubmarketProvider {

    private FleetMemberAPI fleetMember;

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
