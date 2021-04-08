package stelnet.filter.submarket;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.cargostack.HasStack;
import stelnet.helper.CollectionHelper;

public class HasCargoStack implements SubmarketFilter {

    private CargoStackFilter filter;

    public HasCargoStack(CargoStackAPI cargoStack) {
        filter = new HasStack(cargoStack);
    }

    public boolean accept(SubmarketAPI submarket) {
        List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
        CollectionHelper.reduce(cargoStacks, filter);
        return !cargoStacks.isEmpty();
    }
}
