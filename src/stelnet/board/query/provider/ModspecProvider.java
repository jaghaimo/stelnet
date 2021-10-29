package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.AnyShowInCodex;
import stelnet.filter.CargoStackIsType;
import stelnet.filter.Filter;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionUtils;
import stelnet.util.Factory;
import stelnet.util.Settings;

public class ModspecProvider {

    public CargoAPI getModspecs() {
        return getModspecs(Collections.<Filter>emptyList());
    }

    public CargoAPI getModspecs(List<Filter> filters) {
        List<SpecialItemSpecAPI> specialItems = Settings.getAllSpecialItemSpecs();
        CollectionUtils.reduce(specialItems, new AnyShowInCodex());
        return convertToCargo(specialItems, filters);
    }

    private CargoAPI convertToCargo(List<SpecialItemSpecAPI> specialItemSpecs, List<Filter> filters) {
        List<CargoStackAPI> cargoStacks = makeCargoStacks(specialItemSpecs);
        CollectionUtils.reduce(cargoStacks, new CargoStackIsType(CargoStackIsType.Type.MODSPEC));
        CollectionUtils.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    private List<CargoStackAPI> makeCargoStacks(List<SpecialItemSpecAPI> specialItemSpecs) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        for (SpecialItemSpecAPI weaponSpec : specialItemSpecs) {
            cargoStacks.add(makeCargoStack(weaponSpec));
        }
        return cargoStacks;
    }

    private CargoStackAPI makeCargoStack(SpecialItemSpecAPI specialItemSpec) {
        return Factory.createModspecItem(specialItemSpec);
    }
}
