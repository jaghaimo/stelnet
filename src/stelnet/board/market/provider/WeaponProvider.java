package stelnet.board.market.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.weaponspec.ShowInCodex;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionReducer;
import stelnet.util.Factory;
import stelnet.util.Settings;

public class WeaponProvider {

    public CargoAPI getWeapons() {
        return getWeapons(Collections.<CargoStackFilter>emptyList());
    }

    public CargoAPI getWeapons(List<CargoStackFilter> filters) {
        List<WeaponSpecAPI> allWeaponSpecs = Settings.getAllWeaponSpecs();
        CollectionReducer.reduce(allWeaponSpecs, new ShowInCodex());
        return convertToCargo(allWeaponSpecs, filters);
    }

    private CargoAPI convertToCargo(List<WeaponSpecAPI> weaponSpecs, List<CargoStackFilter> filters) {
        List<CargoStackAPI> cargoStacks = makeCargoStacks(weaponSpecs);
        CollectionReducer.reduce(cargoStacks, filters);
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
        return Factory.createWeapon(weaponSpec.getWeaponId());
    }
}
