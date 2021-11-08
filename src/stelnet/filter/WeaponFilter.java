package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public abstract class WeaponFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof CargoStackAPI) {
            return acceptCargoStack((CargoStackAPI) object);
        }
        if (object instanceof WeaponSpecAPI) {
            return acceptWeapon((WeaponSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptCargoStack(CargoStackAPI cargoStack) {
        if (!cargoStack.isWeaponStack()) {
            return true;
        }
        return acceptWeapon(cargoStack.getWeaponSpecIfWeapon());
    }

    protected abstract boolean acceptWeapon(WeaponSpecAPI weapon);
}
