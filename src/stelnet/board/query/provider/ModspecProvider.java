package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.AnyShowInCodexFilter;
import stelnet.filter.Filter;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionUtils;
import stelnet.util.FactoryUtils;
import stelnet.util.SettingsUtils;

public class ModspecProvider {

    public CargoAPI getModspecs() {
        return getModspecs(Collections.<Filter>emptyList());
    }

    public CargoAPI getModspecs(List<Filter> filters) {
        List<HullModSpecAPI> hullModSpecs = SettingsUtils.getAllHullModSpecs();
        CollectionUtils.reduce(hullModSpecs, new AnyShowInCodexFilter());
        return convertToCargo(hullModSpecs, filters);
    }

    private CargoAPI convertToCargo(List<HullModSpecAPI> hullModSpecs, List<Filter> filters) {
        List<CargoStackAPI> cargoStacks = makeCargoStacks(hullModSpecs);
        // CollectionUtils.reduce(cargoStacks, new NotFilter(new CargoStackIsType(CargoStackIsType.Type.MODSPEC)));
        CollectionUtils.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    private List<CargoStackAPI> makeCargoStacks(List<HullModSpecAPI> hullModSpecs) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        for (HullModSpecAPI hullModSpec : hullModSpecs) {
            cargoStacks.add(makeCargoStack(hullModSpec.getId()));
        }
        return cargoStacks;
    }

    private CargoStackAPI makeCargoStack(String hullModId) {
        CargoStackAPI stack = FactoryUtils.createModspecItem(hullModId);
        stack.setSize(1);
        return stack;
    }
}
