package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.EconomyUtils;
import stelnet.util.Excluder;
import stelnet.util.FactoryUtils;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;
import uilib.RenderableShowComponent;
import uilib.ShowShips;
import uilib.property.Size;

public class ShipProvider extends QueryProvider {

    private final FactionProvider factionProvider = new FactionProvider();

    private static final String SUFFIX = "_Hull";
    private static transient List<HullModSpecAPI> allBuiltIns;
    private static transient List<FleetMemberAPI> allFleetMembers;
    private static transient List<ShipHullSpecAPI> allShipHulls;

    public static void reset() {
        allFleetMembers = null;
        allShipHulls = null;
    }

    @Override
    public List<FleetMemberAPI> getMatching(Set<Filter> filters) {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        Set<String> allHullIds = extractHullIds(allShipHullSpecs);
        List<FleetMemberAPI> fleetMembers = convertToFleetMembers(allHullIds);
        CollectionUtils.reduce(fleetMembers, filters);
        Collections.sort(fleetMembers, new ShipHullSorter());
        return fleetMembers;
    }

    @Override
    public RenderableShowComponent getPreview(Set<Filter> filters, Size size) {
        return new ShowShips(
            getMatching(filters),
            L10n.get(QueryL10n.MATCHING_SHIPS),
            L10n.get(QueryL10n.NO_MATCHING_SHIPS),
            size
        );
    }

    @Override
    protected void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    ) {
        List<SubmarketAPI> submarkets = EconomyUtils.getSubmarkets(markets);
        CollectionUtils.reduce(submarkets, Excluder.getQuerySubmarketFilter());
        for (SubmarketAPI submarket : submarkets) {
            MarketAPI market = submarket.getMarket();
            List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
            CollectionUtils.reduce(fleetMembers, filters);
            ResultSet resultSet = new ResultSet(groupingStrategy, market);
            resultSet.addFleetMembers(market, submarket, fleetMembers);
            addToResultSets(resultSets, resultSet);
        }
    }

    public List<HullModSpecAPI> getBuiltIns() {
        if (allBuiltIns == null) {
            List<ShipHullSpecAPI> allShipHulls = getShipHulls();
            Set<String> builtIns = extractBuiltIns(allShipHulls);
            allBuiltIns = convertToHullMods(builtIns);
            Collections.sort(allBuiltIns, new ShipHullSpecSorter());
        }
        return allBuiltIns;
    }

    public Set<String> getBuiltInIds(Set<Filter> filters) {
        List<ShipHullSpecAPI> shipHullsCopy = new LinkedList<>(getShipHulls());
        CollectionUtils.reduce(shipHullsCopy, filters);
        return extractBuiltIns(shipHullsCopy);
    }

    public Set<String> getManufacturers() {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        Set<String> manufacturers = extractManufacturers(allShipHullSpecs);
        return manufacturers;
    }

    private List<ShipHullSpecAPI> getShipHulls() {
        if (allShipHulls == null) {
            allShipHulls = factionProvider.getAllShips();
        }
        return allShipHulls;
    }

    private Set<String> extractBuiltIns(List<ShipHullSpecAPI> shipHulls) {
        Set<String> builtIns = new HashSet<>();
        for (ShipHullSpecAPI shipHull : shipHulls) {
            builtIns.addAll(shipHull.getBuiltInMods());
        }
        return builtIns;
    }

    private Set<String> extractManufacturers(List<ShipHullSpecAPI> shipHulls) {
        Set<String> manufacturers = new TreeSet<>();
        for (ShipHullSpecAPI shipHull : shipHulls) {
            manufacturers.add(shipHull.getManufacturer());
        }
        return manufacturers;
    }

    private Set<String> extractHullIds(List<ShipHullSpecAPI> shipHullSpecs) {
        Set<String> hullIds = new LinkedHashSet<>();
        for (ShipHullSpecAPI shipHullSpec : shipHullSpecs) {
            hullIds.add(shipHullSpec.getHullId());
        }
        return hullIds;
    }

    private List<HullModSpecAPI> convertToHullMods(Set<String> hullModIds) {
        List<HullModSpecAPI> hullMods = new LinkedList<>();
        for (String hullModId : hullModIds) {
            hullMods.add(SettingsUtils.getHullModSpec(hullModId));
        }
        return hullMods;
    }

    private List<FleetMemberAPI> convertToFleetMembers(Set<String> hullIds) {
        if (allFleetMembers == null) {
            allFleetMembers = new LinkedList<>();
            for (String hullId : hullIds) {
                allFleetMembers.add(makeFleetMember(hullId));
            }
        }
        return new LinkedList<FleetMemberAPI>(allFleetMembers);
    }

    private FleetMemberAPI makeFleetMember(String hullId) {
        return FactoryUtils.createFleetMember(hullId + SUFFIX);
    }
}
