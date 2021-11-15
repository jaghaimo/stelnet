package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import lombok.EqualsAndHashCode;
import stelnet.util.SectorUtils;

@EqualsAndHashCode(callSuper = false)
public class CargoStackNotKnownModspec extends CargoStackIsType {

    public CargoStackNotKnownModspec() {
        super(Type.MODSPEC);
    }

    @Override
    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        if (!super.acceptCargoStack(cargoStack)) {
            return true;
        }
        String hullModId = cargoStack.getHullModSpecIfHullMod().getId();
        return !SectorUtils.getPlayerFaction().knowsHullMod(hullModId);
    }

    @Override
    public String toString() {
        return "";
    }
}
