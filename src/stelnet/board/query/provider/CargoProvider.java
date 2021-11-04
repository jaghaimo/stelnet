package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.experimental.ExtensionMethod;
import stelnet.filter.Filter;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionUtils;
import stelnet.util.SettingsUtils;

@ExtensionMethod({ CargoStackExtension.class })
public class CargoProvider extends QueryProvider {

    public CargoAPI getFighters() {
        return getFighters(Collections.<Filter>emptyList());
    }

    public CargoAPI getFighters(List<Filter> filters) {
        List<FighterWingSpecAPI> fighterWings = SettingsUtils.getAllFighterWingSpecs();
        return convertToCargo(fighterWings, filters);
    }

    public CargoAPI getModspecs() {
        return getModspecs(Collections.<Filter>emptyList());
    }

    public CargoAPI getModspecs(List<Filter> filters) {
        List<HullModSpecAPI> hullModSpecs = SettingsUtils.getAllHullModSpecs();
        return convertToCargo(hullModSpecs, filters);
    }

    public CargoAPI getWeapons() {
        return getWeapons(Collections.<Filter>emptyList());
    }

    public CargoAPI getWeapons(List<Filter> filters) {
        List<WeaponSpecAPI> allWeaponSpecs = SettingsUtils.getAllWeaponSpecs();
        return convertToCargo(allWeaponSpecs, filters);
    }

    private <T> CargoAPI convertToCargo(List<T> elements, List<Filter> filters) {
        filter(elements);
        List<CargoStackAPI> cargoStacks = makeCargoStacks(elements);
        CollectionUtils.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    private <T> List<CargoStackAPI> makeCargoStacks(List<T> elements) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        for (T hullModSpec : elements) {
            cargoStacks.add(hullModSpec.asCargoStack());
        }
        return cargoStacks;
    }
}
