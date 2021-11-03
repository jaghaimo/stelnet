package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.AnyShowInCodexFilter;
import stelnet.filter.Filter;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionUtils;
import stelnet.util.FactoryUtils;
import stelnet.util.SettingsUtils;

public class FighterProvider {

    public CargoAPI getFighters() {
        return getFighters(Collections.<Filter>emptyList());
    }

    public CargoAPI getFighters(List<Filter> filters) {
        List<FighterWingSpecAPI> fighterWings = SettingsUtils.getAllFighterWingSpecs();
        CollectionUtils.reduce(fighterWings, new AnyShowInCodexFilter());
        return convertToCargo(fighterWings, filters);
    }

    private CargoAPI convertToCargo(List<FighterWingSpecAPI> fighterWings, List<Filter> filters) {
        List<CargoStackAPI> cargoStacks = makeCargoStacks(fighterWings);
        CollectionUtils.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    private List<CargoStackAPI> makeCargoStacks(List<FighterWingSpecAPI> fighterWings) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        for (FighterWingSpecAPI fighterWing : fighterWings) {
            cargoStacks.add(makeCargoStack(fighterWing.getId()));
        }
        return cargoStacks;
    }

    private CargoStackAPI makeCargoStack(String fighterId) {
        CargoStackAPI stack = FactoryUtils.createFighterItem(fighterId);
        stack.setSize(1);
        return stack;
    }
}
