package stelnet.market.intel.subject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.filter.cargostack.HasStack;
import stelnet.filter.submarket.CanAcquireCargoStack;
import stelnet.filter.submarket.HasCargoStack;
import stelnet.filter.submarket.IsAccessible;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.helper.CargoHelper;
import stelnet.helper.CollectionHelper;

public class CargoSubject extends SubmarketSubject {

    private final CargoStackAPI cargoStack;
    private Map<SubmarketAPI, CargoStackAPI> submarketsWithCargoStack;

    public CargoSubject(CargoStackAPI c, MarketAPI m) {
        super(c.getDisplayName(), m);
        cargoStack = c;
    }

    @Override
    public String getIcon() {
        if (cargoStack.isWeaponStack()) {
            return cargoStack.getWeaponSpecIfWeapon().getTurretSpriteName();
        }

        if (cargoStack.isFighterWingStack()) {
            return cargoStack.getFighterWingSpecIfWing().getId();
        }

        if (cargoStack.isSpecialStack()) {
            return cargoStack.getSpecialItemSpecIfSpecial().getIconName();
        }

        return super.getIcon();
    }

    @Override
    public boolean isAvailable() {
        List<SubmarketFilter> filters = Arrays.asList(
                new HasCargoStack(cargoStack),
                new IsAccessible(),
                new CanAcquireCargoStack(cargoStack)
        );
        List<SubmarketAPI> submarkets = findSubmarkets();
        CollectionHelper.reduce(submarkets, filters);
        return !submarkets.isEmpty();
    }

    @Override
    protected void addSubmarket(TooltipMakerAPI info, SubmarketAPI submarket) {
        super.addSubmarket(info, submarket);
        CargoAPI cargo = CargoHelper.createCargo(false);
        cargo.addFromStack(submarketsWithCargoStack.get(submarket));
        info.showCargo(cargo, 1, false, 3f);
    }

    @Override
    protected int getEntityCount() {
        float count = 0;
        for (CargoStackAPI stack : submarketsWithCargoStack.values()) {
            count += stack.getSize();
        }
        return (int) count;
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new HasCargoStack(cargoStack);
    }

    @Override
    protected int getSubmarketCount() {
        return submarketsWithCargoStack.size();
    }

    @Override
    protected Set<SubmarketAPI> getSubmarkets() {
        return submarketsWithCargoStack.keySet();
    }

    @Override
    protected void populate() {
        submarketsWithCargoStack = new HashMap<>();
        for (SubmarketAPI submarket : findSubmarkets()) {
            submarketsWithCargoStack.put(submarket, getCargoStack(submarket, cargoStack));
        }
    }

    private CargoStackAPI getCargoStack(SubmarketAPI submarket, CargoStackAPI cargoStack) {
        List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
        CollectionHelper.reduce(cargoStacks, new HasStack(cargoStack));
        if (cargoStacks.isEmpty()) {
            return cargoStack;
        }
        return cargoStacks.get(0);
    }
}
