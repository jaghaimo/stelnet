package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.FighterWingAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.WingRole;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CargoStackWingIsRole extends Filter {

    private final WingRole wingRole;

    @Override
    public boolean accept(Object object) {
        if (object instanceof CargoStackAPI) {
            return acceptCargoStack((CargoStackAPI) object);
        }
        if (object instanceof FighterWingAPI) {
            return acceptWing((FighterWingAPI) object);
        }
        if (object instanceof FighterWingSpecAPI) {
            return acceptWingSpec((FighterWingSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        if (cargoStack.isFighterWingStack()) {
            return acceptWingSpec(cargoStack.getFighterWingSpecIfWing());
        }
        return true;
    }

    protected boolean acceptWing(FighterWingAPI wing) {
        return wing.getRole().equals(wingRole);
    }

    protected boolean acceptWingSpec(FighterWingSpecAPI wingSpec) {
        return wingSpec.getRole().equals(wingRole);
    }
}
