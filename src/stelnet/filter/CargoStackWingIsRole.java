package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.FighterWingAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.WingRole;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class CargoStackWingIsRole extends CargoStackFilter {

    private final WingRole wingRole;

    @Override
    public boolean accept(final Object object) {
        if (object instanceof FighterWingAPI) {
            return acceptWing((FighterWingAPI) object);
        }
        if (object instanceof FighterWingSpecAPI) {
            return acceptWingSpec((FighterWingSpecAPI) object);
        }
        return super.accept(object);
    }

    @Override
    public String toString() {
        return L10n.common("ROLE_" + wingRole.name());
    }

    @Override
    protected boolean acceptCargoStack(final CargoStackAPI cargoStack) {
        if (cargoStack.isFighterWingStack()) {
            return acceptWingSpec(cargoStack.getFighterWingSpecIfWing());
        }
        return true;
    }

    protected boolean acceptWing(final FighterWingAPI wing) {
        return wing.getRole().equals(wingRole);
    }

    protected boolean acceptWingSpec(final FighterWingSpecAPI wingSpec) {
        return wingSpec.getRole().equals(wingRole);
    }
}
