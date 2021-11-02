package stelnet.market_old.intel.subject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.filter.fleetmember.HasMember;
import stelnet.filter.submarket.CanAcquireFleetMember;
import stelnet.filter.submarket.HasFleetMember;
import stelnet.filter.submarket.IsAccessible;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.helper.CollectionHelper;

public class ShipSubject extends SubmarketSubject {

    private final FleetMemberAPI ship;
    private Map<SubmarketAPI, List<FleetMemberAPI>> submarketsWithFleetMembers;

    public ShipSubject(FleetMemberAPI s, MarketAPI m) {
        super(s.getHullSpec().getHullNameWithDashClass() + " " + s.getHullSpec().getDesignation(), m);
        ship = s;
    }

    @Override
    public String getIcon() {
        return ship.getHullSpec().getSpriteName();
    }

    @Override
    public boolean isAvailable() {
        List<SubmarketAPI> submarkets = findSubmarkets();
        List<SubmarketFilter> filters = Arrays.asList(
                new HasFleetMember(ship),
                new CanAcquireFleetMember(ship),
                new IsAccessible()
        );
        CollectionHelper.reduce(submarkets, filters);
        return !submarkets.isEmpty();
    }

    @Override
    protected void addSubmarket(TooltipMakerAPI info, SubmarketAPI submarket) {
        super.addSubmarket(info, submarket);
        info.showShips(submarketsWithFleetMembers.get(submarket), 1, false, 3f);
    }

    @Override
    protected int getEntityCount() {
        int count = 0;
        for (List<FleetMemberAPI> fleetMembers : submarketsWithFleetMembers.values()) {
            count += fleetMembers.size();
        }
        return count;
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new HasFleetMember(ship);
    }

    @Override
    protected int getSubmarketCount() {
        return submarketsWithFleetMembers.size();
    }

    @Override
    protected Set<SubmarketAPI> getSubmarkets() {
        return submarketsWithFleetMembers.keySet();
    }

    @Override
    protected void populate() {
        submarketsWithFleetMembers = new HashMap<>();
        for (SubmarketAPI submarket : findSubmarkets()) {
            submarketsWithFleetMembers.put(submarket, getFleetMembers(submarket, ship));
        }
    }

    private List<FleetMemberAPI> getFleetMembers(SubmarketAPI submarket, FleetMemberAPI fleetMember) {
        List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
        CollectionHelper.reduce(fleetMembers, new HasMember(fleetMember));
        return fleetMembers;
    }
}
