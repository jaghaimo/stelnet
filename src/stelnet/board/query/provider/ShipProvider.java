package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.FactoryUtils;

public class ShipProvider extends QueryProvider {

    private static final String SUFFIX = "_Hull";

    public List<FleetMemberAPI> getShips() {
        return getShips(Collections.<Filter>emptyList());
    }

    public List<FleetMemberAPI> getShips(List<Filter> filters) {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        Set<String> allHullIds = getHullIds(allShipHullSpecs);
        return convertToFleetMembers(allHullIds, filters);
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
        return FactoryUtils.createFleetMember(hullId + SUFFIX);
    }
}
