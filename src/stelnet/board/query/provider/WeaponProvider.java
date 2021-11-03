package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.AnyShowInCodexFilter;
import stelnet.filter.Filter;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionUtils;
import stelnet.util.Factory;
import stelnet.util.Settings;

public class WeaponProvider {

    public CargoAPI getWeapons() {
        return getWeapons(Collections.<Filter>emptyList());
    }

    public CargoAPI getWeapons(List<Filter> filters) {
        List<WeaponSpecAPI> allWeaponSpecs = Settings.getAllWeaponSpecs();
        CollectionUtils.reduce(allWeaponSpecs, new AnyShowInCodexFilter());
        return convertToCargo(allWeaponSpecs, filters);
    }

    private CargoAPI convertToCargo(List<WeaponSpecAPI> weaponSpecs, List<Filter> filters) {
        List<CargoStackAPI> cargoStacks = makeCargoStacks(weaponSpecs);
        CollectionUtils.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    private List<CargoStackAPI> makeCargoStacks(List<WeaponSpecAPI> weaponSpecs) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        for (WeaponSpecAPI weaponSpec : weaponSpecs) {
            cargoStacks.add(makeCargoStack(weaponSpec));
        }
        return cargoStacks;
    }

    private CargoStackAPI makeCargoStack(WeaponSpecAPI weaponSpec) {
        CargoStackAPI stack = Factory.createWeaponItem(weaponSpec.getWeaponId());
        stack.setSize(1);
        return stack;
    }
}
