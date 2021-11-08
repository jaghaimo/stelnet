package stelnet.filter;

import com.fs.starfarer.api.loading.WeaponSpecAPI;

public abstract class WeaponFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof WeaponSpecAPI) {
            return acceptWeapon((WeaponSpecAPI) object);
        }
        return super.accept(object);
    }

    protected abstract boolean acceptWeapon(WeaponSpecAPI weapon);
}
