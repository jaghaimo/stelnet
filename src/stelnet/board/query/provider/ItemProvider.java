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

    public List<CargoStackAPI> getItems(List<Filter> filters) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        addAsCargoStacks(cargoStacks, getFighters());
        addAsCargoStacks(cargoStacks, getModspecs());
        addAsCargoStacks(cargoStacks, getWeapons());
        CollectionUtils.reduce(cargoStacks, filters);
        return cargoStacks;
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
        List<FighterWingSpecAPI> allFighterWings = SettingsUtils.getAllFighterWingSpecs();
        filter(allFighterWings);
        return allFighterWings;
    }

    public List<HullModSpecAPI> getModspecs() {
        List<HullModSpecAPI> allHullModSpecs = SettingsUtils.getAllHullModSpecs();
        filter(allHullModSpecs);
        return allHullModSpecs;
    }

    public List<WeaponSpecAPI> getWeapons() {
        List<WeaponSpecAPI> allWeaponSpecs = SettingsUtils.getAllWeaponSpecs();
        filter(allWeaponSpecs);
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
