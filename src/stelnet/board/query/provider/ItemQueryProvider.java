package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.experimental.ExtensionMethod;
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
import uilib.ShowCargo;
import uilib.property.Size;

@ExtensionMethod({ CargoStackExtension.class })
public class ItemQueryProvider extends QueryProvider {

    private static transient List<CargoStackAPI> allCargoStacks;
    private static transient List<FighterWingSpecAPI> allFighterWings;
    private static transient List<HullModSpecAPI> allHullModSpecs;
    private static transient List<WeaponSpecAPI> allWeaponSpecs;

    public static void resetCache() {
        allCargoStacks = null;
        allFighterWings = null;
        allHullModSpecs = null;
        allWeaponSpecs = null;
    }

    private final FactionProvider factionProvider = new FactionProvider();

    @Override
    public Set<Filter> getAdditionalFilters(final QueryManager manager) {
        final Set<Filter> resultFilters = super.getAdditionalFilters(manager);
        resultFilters.add(new LogicalOr(manager.getSubmarketFilters(), "submarkets"));
        return resultFilters;
    }

    @Override
    public List<CargoStackAPI> getMatching(final Set<Filter> filters) {
        if (allCargoStacks == null) {
            allCargoStacks = new LinkedList<>();
            addAsCargoStacks(allCargoStacks, getFighters());
            addAsCargoStacks(allCargoStacks, getModspecs());
            addAsCargoStacks(allCargoStacks, getWeapons());
        }
        final List<CargoStackAPI> cargoStacksCopy = new LinkedList<CargoStackAPI>(allCargoStacks);
        CollectionUtils.reduce(cargoStacksCopy, filters);
        return cargoStacksCopy;
    }

    @Override
    public RenderableShowComponent getPreview(final Set<Filter> filters, final Size size) {
        final CargoAPI cargo = StelnetHelper.makeCargoFromStacks(getMatching(filters));
        return new ShowCargo(cargo, L10n.query("MATCHING_ITEMS"), L10n.query("NO_MATCHING_ITEMS"), size);
    }

    public Set<String> getManufacturers() {
        final List<FighterWingSpecAPI> allFighterWings = getFighters();
        final List<WeaponSpecAPI> allWeaponSpecs = getWeapons();
        final Set<String> manufacturers = new TreeSet<>();
        manufacturers.addAll(extractManufacturers(allFighterWings));
        manufacturers.addAll(extractManufacturers(allWeaponSpecs));
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
            final List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
            CollectionUtils.reduce(cargoStacks, filters);
            final ResultSet resultSet = new ResultSet(groupingStrategy, market);
            resultSet.addCargoStacks(market, submarket, cargoStacks);
            addToResultSets(resultSets, resultSet);
        }
    }

    private List<FighterWingSpecAPI> getFighters() {
        if (allFighterWings == null) {
            allFighterWings = factionProvider.getAllFighters();
        }
        return allFighterWings;
    }

    private List<HullModSpecAPI> getModspecs() {
        if (allHullModSpecs == null) {
            allHullModSpecs = factionProvider.getAllHullMods();
        }
        return allHullModSpecs;
    }

    private List<WeaponSpecAPI> getWeapons() {
        if (allWeaponSpecs == null) {
            allWeaponSpecs = factionProvider.getAllWeapons();
        }
        return allWeaponSpecs;
    }

    private <T> Set<String> extractManufacturers(final List<T> elements) {
        final Set<String> manufacturers = new TreeSet<>();
        for (final T element : elements) {
            manufacturers.add(element.getManufacturer());
        }
        return manufacturers;
    }

    private <T> void addAsCargoStacks(final List<CargoStackAPI> cargoStacks, final List<T> elements) {
        for (final T element : elements) {
            cargoStacks.add(element.asCargoStack());
        }
    }
}
