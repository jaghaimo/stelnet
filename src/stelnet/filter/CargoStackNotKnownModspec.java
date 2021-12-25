package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import lombok.EqualsAndHashCode;
import stelnet.filter.CargoStackIsType.Type;
import stelnet.util.SectorUtils;

@EqualsAndHashCode(callSuper = false)
public final class CargoStackNotKnownModspec extends CargoStackFilter {

    private final Filter isCargoStack = new CargoStackIsType(Type.MODSPEC);

    @Override
    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        if (!isCargoStack.accept(cargoStack)) {
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
