package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.experimental.ExtensionMethod;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.SettingsUtils;

@ExtensionMethod({ CargoStackExtension.class })
public class ItemProvider extends FilterableProvider {

    private transient List<CargoStackAPI> allCargoStacks;
    private transient List<FighterWingSpecAPI> allFighterWings;
    private transient List<HullModSpecAPI> allHullModSpecs;
    private transient List<WeaponSpecAPI> allWeaponSpecs;

    public List<CargoStackAPI> getItems(List<Filter> filters) {
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
            allFighterWings = SettingsUtils.getAllFighterWingSpecs();
            filter(allFighterWings);
        }
        return allFighterWings;
    }

    public List<HullModSpecAPI> getModspecs() {
        if (allHullModSpecs == null) {
            allHullModSpecs = SettingsUtils.getAllHullModSpecs();
            filter(allHullModSpecs);
        }
        return allHullModSpecs;
    }

    public List<WeaponSpecAPI> getWeapons() {
        if (allWeaponSpecs == null) {
            allWeaponSpecs = SettingsUtils.getAllWeaponSpecs();
            filter(allWeaponSpecs);
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
