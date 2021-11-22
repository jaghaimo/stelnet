package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.DModManager;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import stelnet.board.query.ResultSet;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.FactoryUtils;

public class ShipProvider extends QueryProvider {

    private final FactionProvider factionProvider = new FactionProvider();

    private static final String SUFFIX = "_Hull";
    private static transient List<HullModSpecAPI> allDmods;
    private static transient List<FleetMemberAPI> allFleetMembers;
    private static transient List<ShipHullSpecAPI> allShipHulls;

    public static void reset() {
        allDmods = null;
        allFleetMembers = null;
        allShipHulls = null;
    }

    public ShipProvider(QueryFactory factory) {
        super(factory);
    }

    @Override
    public List<FleetMemberAPI> getMatching(Set<Filter> filters) {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        Set<String> allHullIds = getHullIds(allShipHullSpecs);
        List<FleetMemberAPI> fleetMembers = convertToFleetMembers(allHullIds, filters);
        Collections.sort(fleetMembers, new ShipHullSorter());
        return fleetMembers;
    }

    @Override
    protected void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final boolean groupBySystem
    ) {
        for (MarketAPI market : markets) {
            for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
                List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
                CollectionUtils.reduce(fleetMembers, filters);
                ResultSet resultSet = new ResultSet(groupBySystem, market);
                resultSet.addFleetMembers(market, submarket, fleetMembers);
                addToResultSets(resultSets, resultSet);
            }
        }
    }

    public List<HullModSpecAPI> getDmods() {
        if (allDmods == null) {
            allDmods = DModManager.getModsWithTags(Tags.HULLMOD_DAMAGE);
            Collections.sort(allDmods, new ShipDmodSorter());
        }
        return allDmods;
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
            allShipHulls = factionProvider.getAllShips();
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

    private List<FleetMemberAPI> convertToFleetMembers(Set<String> hullIds, Set<Filter> filters) {
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
