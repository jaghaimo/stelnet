package stelnet.filter.submarket;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SubmarketPlugin.TransferAction;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

public class CanAcquireCargoStack implements SubmarketFilter {

    private final CargoStackAPI cargoStack;

    public CanAcquireCargoStack(CargoStackAPI cargoStack) {
        this.cargoStack = cargoStack;
    }

    public boolean accept(SubmarketAPI submarket) {
        return !submarket.getPlugin().isIllegalOnSubmarket(cargoStack, TransferAction.PLAYER_BUY);
    }
}
