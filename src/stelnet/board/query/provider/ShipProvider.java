package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import stelnet.board.query.ResultSet;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.FactoryUtils;
import stelnet.util.SettingsUtils;

public class ShipProvider extends QueryProvider {

    private static final String SUFFIX = "_Hull";
    private transient List<FleetMemberAPI> allFleetMembers;
    private transient List<ShipHullSpecAPI> allShipHulls;

    @Override
    public List<FleetMemberAPI> getMatching(List<Filter> filters) {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        Set<String> allHullIds = getHullIds(allShipHullSpecs);
        List<FleetMemberAPI> fleetMembers = convertToFleetMembers(allHullIds, filters);
        return fleetMembers;
    }

    @Override
    protected void processMarkets(List<ResultSet> resultSets, List<MarketAPI> markets, List<Filter> filters) {
        for (MarketAPI market : markets) {
            for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
                List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
                CollectionUtils.reduce(fleetMembers, filters);
                ResultSet resultSet = new ResultSet(market);
                resultSet.addFleetMembers(market, submarket, fleetMembers);
                resultSets.add(resultSet);
            }
        }
    }

    public Set<String> getManufacturers() {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        Set<String> manufacturers = extractManufacturers(allShipHullSpecs);
        return manufacturers;
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
        if (allFleetMembers == null) {
            allFleetMembers = new LinkedList<>();
            for (String hullId : hullIds) {
                allFleetMembers.add(makeFleetMember(hullId));
            }
        }
        List<FleetMemberAPI> fleetMembersCopy = new LinkedList<FleetMemberAPI>(allFleetMembers);
        CollectionUtils.reduce(fleetMembersCopy, filters);
        return fleetMembersCopy;
    }

    private FleetMemberAPI makeFleetMember(String hullId) {
        return FactoryUtils.createFleetMember(hullId + SUFFIX);
    }
}
