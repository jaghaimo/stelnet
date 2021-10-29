package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
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
        List<HullModSpecAPI> specialItems = Settings.getAllHullModSpecs();
        CollectionUtils.reduce(specialItems, new AnyShowInCodex());
        return convertToCargo(specialItems, filters);
    }

    private CargoAPI convertToCargo(List<HullModSpecAPI> hullModSpecs, List<Filter> filters) {
        List<CargoStackAPI> cargoStacks = makeCargoStacks(hullModSpecs);
        CollectionUtils.reduce(cargoStacks, new CargoStackIsType(CargoStackIsType.Type.MODSPEC));
        CollectionUtils.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    private List<CargoStackAPI> makeCargoStacks(List<HullModSpecAPI> hullModSpecs) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        for (HullModSpecAPI weaponSpec : hullModSpecs) {
            cargoStacks.add(makeCargoStack(weaponSpec));
        }
        return cargoStacks;
    }

    private CargoStackAPI makeCargoStack(HullModSpecAPI hullModSpec) {
        CargoStackAPI stack = Factory.createModspecItem(hullModSpec.getId());
        stack.setSize(1);
        return stack;
    }
}
