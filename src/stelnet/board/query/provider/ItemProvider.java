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
import stelnet.board.query.QueryL10n;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableShowComponent;
import uilib.ShowCargo;
import uilib.property.Size;

@ExtensionMethod({ CargoStackExtension.class })
public class ItemProvider extends QueryProvider {

    private final FactionProvider factionProvider = new FactionProvider();

    private static transient List<CargoStackAPI> allCargoStacks;
    private static transient List<FighterWingSpecAPI> allFighterWings;
    private static transient List<HullModSpecAPI> allHullModSpecs;
    private static transient List<WeaponSpecAPI> allWeaponSpecs;

    public static void reset() {
        allCargoStacks = null;
        allFighterWings = null;
        allHullModSpecs = null;
        allWeaponSpecs = null;
    }

    @Override
    public List<CargoStackAPI> getMatching(Set<Filter> filters) {
        if (allCargoStacks == null) {
            allCargoStacks = new LinkedList<>();
            addAsCargoStacks(allCargoStacks, getFighters());
            addAsCargoStacks(allCargoStacks, getModspecs());
            addAsCargoStacks(allCargoStacks, getWeapons());
        }
        List<CargoStackAPI> cargoStacksCopy = new LinkedList<CargoStackAPI>(allCargoStacks);
        CollectionUtils.reduce(cargoStacksCopy, filters);
        return cargoStacksCopy;
    }

    @Override
    public RenderableShowComponent getPreview(Set<Filter> filters, Size size) {
        CargoAPI cargo = StelnetHelper.makeCargoFromStacks(getMatching(filters));
        return new ShowCargo(cargo, L10n.get(QueryL10n.MATCHING_ITEMS), L10n.get(QueryL10n.NO_MATCHING_ITEMS), size);
    }

    @Override
    protected void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    ) {
        List<SubmarketAPI> submarkets = StelnetHelper.getSubmarkets(markets);
        CollectionUtils.reduce(submarkets, Excluder.getQuerySubmarketFilter());
        for (SubmarketAPI submarket : submarkets) {
            MarketAPI market = submarket.getMarket();
            List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
            CollectionUtils.reduce(cargoStacks, filters);
            ResultSet resultSet = new ResultSet(groupingStrategy, market);
            resultSet.addCargoStacks(market, submarket, cargoStacks);
            addToResultSets(resultSets, resultSet);
        }
    }

    public Set<String> getManufacturers() {
        List<FighterWingSpecAPI> allFighterWings = getFighters();
        List<WeaponSpecAPI> allWeaponSpecs = getWeapons();
        Set<String> manufacturers = new TreeSet<>();
        manufacturers.addAll(extractManufacturers(allFighterWings));
        manufacturers.addAll(extractManufacturers(allWeaponSpecs));
        return manufacturers;
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

    private <T> Set<String> extractManufacturers(List<T> elements) {
        Set<String> manufacturers = new TreeSet<>();
        for (T element : elements) {
            manufacturers.add(element.getManufacturer());
        }
        return manufacturers;
    }

    private <T> void addAsCargoStacks(List<CargoStackAPI> cargoStacks, List<T> elements) {
        for (T hullModSpec : elements) {
            cargoStacks.add(hullModSpec.asCargoStack());
        }
    }
}
