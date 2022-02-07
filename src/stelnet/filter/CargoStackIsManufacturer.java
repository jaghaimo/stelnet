package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class CargoStackIsManufacturer extends CargoStackFilter {

    private final String manufacturer;

    @Override
    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        if (cargoStack.isFighterWingStack()) {
            return acceptWingSpec(cargoStack.getFighterWingSpecIfWing());
        }
        if (cargoStack.isWeaponStack()) {
            return acceptWeaponSpec(cargoStack.getWeaponSpecIfWeapon());
        }
        return true;
    }

    private boolean acceptWingSpec(FighterWingSpecAPI wingSpec) {
        return wingSpec.getVariant().getHullSpec().getManufacturer().equals(manufacturer);
    }

    private boolean acceptWeaponSpec(WeaponSpecAPI weaponSpec) {
        return weaponSpec.getManufacturer().equals(manufacturer);
    }

    @Override
    public String toString() {
        return manufacturer;
    }
}
