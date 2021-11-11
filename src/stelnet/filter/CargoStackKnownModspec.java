package stelnet.filter;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class CargoStackKnownModspec extends CargoStackIsType {

    public CargoStackKnownModspec() {
        super(Type.MODSPEC);
    }

    @Override
    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        if (!super.acceptCargoStack(cargoStack)) {
            return false;
        }
        String hullModId = cargoStack.getHullModSpecIfHullMod().getId();
        return Global.getSector().getPlayerFaction().knowsHullMod(hullModId);
    }
}
