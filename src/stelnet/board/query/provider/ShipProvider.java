package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.FactoryUtils;
import stelnet.util.SettingsUtils;

public class ShipProvider extends FilterableProvider {

    private static final String SUFFIX = "_Hull";
    private transient List<ShipHullSpecAPI> allShipHulls;

    public List<FleetMemberAPI> getShips() {
        return getShips(Collections.<Filter>emptyList());
    }

    public List<FleetMemberAPI> getShips(List<Filter> filters) {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        Set<String> allHullIds = getHullIds(allShipHullSpecs);
        return convertToFleetMembers(allHullIds, filters);
    }

    public Set<String> getManufacturers() {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        return extractManufacturers(allShipHullSpecs);
    }

    protected Set<String> extractManufacturers(List<ShipHullSpecAPI> shipHulls) {
        Set<String> manufacturers = new TreeSet<>();
        for (ShipHullSpecAPI shipHull : shipHulls) {
            manufacturers.add(shipHull.getManufacturer());
        }
        return manufacturers;
    }

    protected List<ShipHullSpecAPI> getShipHulls() {
        if (allShipHulls == null) {
            allShipHulls = SettingsUtils.getAllShipHullSpecs();
            filter(allShipHulls);
        }
        return allShipHulls;
    }

    private Set<String> getHullIds(List<ShipHullSpecAPI> shipHullSpecs) {
        Set<String> hullIds = new LinkedHashSet<>();
        for (ShipHullSpecAPI shipHullSpec : shipHullSpecs) {
            hullIds.add(shipHullSpec.getHullId());
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
