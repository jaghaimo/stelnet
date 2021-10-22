package stelnet.filter.submarket;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import java.util.List;
import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.cargostack.HasStack;
import stelnet.util.CollectionReducer;

public class HasCargoStack implements SubmarketFilter {

    private final CargoStackFilter filter;

    public HasCargoStack(CargoStackAPI cargoStack) {
        filter = new HasStack(cargoStack);
    }

    public boolean accept(SubmarketAPI submarket) {
        List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
        CollectionReducer.reduce(cargoStacks, filter);
        return !cargoStacks.isEmpty();
    }
}
