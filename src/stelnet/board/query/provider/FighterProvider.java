package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.AnyShowInCodex;
import stelnet.filter.Filter;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionUtils;
import stelnet.util.Factory;
import stelnet.util.Settings;

public class FighterProvider {

    public CargoAPI getFighters() {
        return getFighters(Collections.<Filter>emptyList());
    }

    public CargoAPI getFighters(List<Filter> filters) {
        List<FighterWingSpecAPI> fighterWings = Settings.getAllFighterWingSpecs();
        CollectionUtils.reduce(fighterWings, new AnyShowInCodex());
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
            cargoStacks.add(makeCargoStack(fighterWing));
        }
        return cargoStacks;
    }

    private CargoStackAPI makeCargoStack(FighterWingSpecAPI fighterWing) {
        return Factory.createFighterItem(fighterWing);
    }
}
