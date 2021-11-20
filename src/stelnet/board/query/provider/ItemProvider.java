package stelnet.board.query.provider;

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
import stelnet.board.query.ResultSet;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;

@ExtensionMethod({ CargoStackExtension.class })
public class ItemProvider extends QueryProvider {

    private final FactionProvider factionProvider = new FactionProvider();

    private transient List<CargoStackAPI> allCargoStacks;
    private transient List<FighterWingSpecAPI> allFighterWings;
    private transient List<HullModSpecAPI> allHullModSpecs;
    private transient List<WeaponSpecAPI> allWeaponSpecs;

    public ItemProvider(QueryFactory factory) {
        super(factory);
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
    protected void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final boolean groupBySystem
    ) {
        for (MarketAPI market : markets) {
            for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
                List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
                CollectionUtils.reduce(cargoStacks, filters);
                ResultSet resultSet = new ResultSet(groupBySystem, market);
                resultSet.addCargoStacks(market, submarket, cargoStacks);
                addToResultSets(resultSets, resultSet);
            }
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
