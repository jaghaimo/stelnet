package stelnet.board.query.provider;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.*;
import stelnet.board.query.QueryManager;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableShowComponent;
import uilib.ShowShips;
import uilib.property.Size;

public class ShipQueryProvider extends QueryProvider {

    private static final String SUFFIX = "_Hull";

    private static transient List<HullModSpecAPI> allBuiltIns;
    private static transient List<FleetMemberAPI> allFleetMembers;
    private static transient List<ShipHullSpecAPI> allShipHulls;

    public static void resetCache() {
        allBuiltIns = null;
        allFleetMembers = null;
        allShipHulls = null;
    }

    private final FactionProvider factionProvider = new FactionProvider();

    @Override
    public Set<Filter> getAdditionalFilters(final QueryManager manager) {
        final Set<Filter> resultFilters = super.getAdditionalFilters(manager);
        resultFilters.add(new LogicalOr(manager.getSubmarketFilters(), "submarkets"));
        resultFilters.addAll(manager.getDModCountFilters());
        resultFilters.addAll(manager.getDModTypesFilters());
        return resultFilters;
    }

    @Override
    public List<FleetMemberAPI> getMatching(final Set<Filter> filters) {
        final List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        final Set<String> allHullIds = extractHullIds(allShipHullSpecs);
        final List<FleetMemberAPI> fleetMembers = convertToFleetMembers(allHullIds);
        CollectionUtils.reduce(fleetMembers, filters);
        Collections.sort(fleetMembers, new ShipHullSorter());
        return fleetMembers;
    }

    @Override
    public RenderableShowComponent getPreview(final Set<Filter> filters, final Size size) {
        return new ShowShips(getMatching(filters), L10n.query("MATCHING_SHIPS"), L10n.query("NO_MATCHING_SHIPS"), size);
    }

    public List<HullModSpecAPI> getBuiltIns() {
        if (allBuiltIns == null) {
            final List<ShipHullSpecAPI> allShipHulls = getShipHulls();
            final Set<String> builtIns = extractBuiltIns(allShipHulls);
            allBuiltIns = convertToHullMods(builtIns);
            Collections.sort(allBuiltIns, new ShipHullSpecSorter());
        }
        return allBuiltIns;
    }

    public Set<String> getBuiltInIds(final Set<Filter> filters) {
        final List<ShipHullSpecAPI> shipHullsCopy = new LinkedList<>(getShipHulls());
        CollectionUtils.reduce(shipHullsCopy, filters);
        return extractBuiltIns(shipHullsCopy);
    }

    public Set<String> getManufacturers() {
        final List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        final Set<String> manufacturers = extractManufacturers(allShipHullSpecs);
        return manufacturers;
    }

    @Override
    protected void processMarkets(
        final List<ResultSet> resultSets,
        final List<MarketAPI> markets,
        final Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    ) {
        final List<SubmarketAPI> submarkets = StelnetHelper.getSubmarkets(markets);
        CollectionUtils.reduce(submarkets, Excluder.getSubmarketFilter());
        for (final SubmarketAPI submarket : submarkets) {
            final MarketAPI market = submarket.getMarket();
            final List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
            CollectionUtils.reduce(fleetMembers, filters);
            final ResultSet resultSet = new ResultSet(groupingStrategy, market);
            resultSet.addFleetMembers(market, submarket, fleetMembers);
            addToResultSets(resultSets, resultSet);
        }
    }

    private List<ShipHullSpecAPI> getShipHulls() {
        allShipHulls = null;
        if (allShipHulls == null) {
            allShipHulls = factionProvider.getAllShips();
        }
        return allShipHulls;
    }

    private Set<String> extractBuiltIns(final List<ShipHullSpecAPI> shipHulls) {
        final Set<String> builtIns = new HashSet<>();
        for (final ShipHullSpecAPI shipHull : shipHulls) {
            builtIns.addAll(shipHull.getBuiltInMods());
        }
        return builtIns;
    }

    private Set<String> extractManufacturers(final List<ShipHullSpecAPI> shipHulls) {
        final Set<String> manufacturers = new TreeSet<>();
        for (final ShipHullSpecAPI shipHull : shipHulls) {
            manufacturers.add(shipHull.getManufacturer());
        }
        return manufacturers;
    }

    private Set<String> extractHullIds(final List<ShipHullSpecAPI> shipHullSpecs) {
        final Set<String> hullIds = new LinkedHashSet<>();
        for (final ShipHullSpecAPI shipHullSpec : shipHullSpecs) {
            hullIds.add(shipHullSpec.getHullId());
        }
        return hullIds;
    }

    private List<HullModSpecAPI> convertToHullMods(final Set<String> hullModIds) {
        final List<HullModSpecAPI> hullMods = new LinkedList<>();
        for (final String hullModId : hullModIds) {
            hullMods.add(Global.getSettings().getHullModSpec(hullModId));
        }
        return hullMods;
    }

    private List<FleetMemberAPI> convertToFleetMembers(final Set<String> hullIds) {
        if (allFleetMembers == null) {
            allFleetMembers = new LinkedList<>();
            for (final String hullId : hullIds) {
                allFleetMembers.add(makeFleetMember(hullId));
            }
        }
        return new LinkedList<FleetMemberAPI>(allFleetMembers);
    }

    private FleetMemberAPI makeFleetMember(final String hullId) {
        return Global.getFactory().createFleetMember(FleetMemberType.SHIP, hullId + SUFFIX);
    }
}
