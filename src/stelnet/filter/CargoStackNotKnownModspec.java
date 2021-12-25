package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import lombok.EqualsAndHashCode;
import stelnet.util.SectorUtils;

@EqualsAndHashCode(callSuper = false)
public final class CargoStackNotKnownModspec extends CargoStackFilter {

    @Override
    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        if (!cargoStack.isModSpecStack()) {
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
