package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.filter.AnyShowInCodex;
import stelnet.filter.Filter;
import stelnet.filter.NotFilter;
import stelnet.filter.ShipBaseHullId;
import stelnet.filter.ShipHullIsSize;
import stelnet.util.CollectionUtils;
import stelnet.util.Factory;
import stelnet.util.Settings;

public class ShipProvider {

    private static final String SUFFIX = "_Hull";

    public List<FleetMemberAPI> getShips() {
        return getShips(Collections.<Filter>emptyList());
    }

    public List<FleetMemberAPI> getShips(List<Filter> filters) {
        List<ShipHullSpecAPI> allShipHullSpecs = Settings.getAllShipHullSpecs();
        CollectionUtils.reduce(allShipHullSpecs, getCommonFilters());
        Set<String> allHullIds = getHullIds(allShipHullSpecs);
        return convertToFleetMembers(allHullIds, filters);
    }

    private List<Filter> getCommonFilters() {
        return Arrays.asList(
            new AnyShowInCodex(),
            new NotFilter(new ShipHullIsSize(ShipAPI.HullSize.FIGHTER)),
            new NotFilter(new ShipBaseHullId("gargoyle")),
            new NotFilter(new ShipBaseHullId("merlon")),
            new NotFilter(new ShipBaseHullId("ravelin"))
        );
    }

    private Set<String> getHullIds(List<ShipHullSpecAPI> shipHullSpecs) {
        Set<String> hullIds = new LinkedHashSet<>();
        for (ShipHullSpecAPI shipHullSpec : shipHullSpecs) {
            hullIds.add(shipHullSpec.getBaseHullId());
        }
        return hullIds;
    }

    private List<FleetMemberAPI> convertToFleetMembers(Set<String> hullIds, List<Filter> filters) {
        List<FleetMemberAPI> members = new LinkedList<>();
        for (String hullId : hullIds) {
            members.add(makeFleetMember(hullId));
        }
        CollectionUtils.reduce(members, filters);
        return members;
    }

    private FleetMemberAPI makeFleetMember(String hullId) {
        return Factory.createFleetMember(hullId + SUFFIX);
    }
}
