package stelnet.filter;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeaponIsType extends WeaponFilter {

    private final WeaponType weaponType;

    @Override
    protected boolean acceptWeapon(WeaponSpecAPI weapon) {
        boolean isType = weapon.getType().equals(weaponType);
        boolean isMountType = weapon.getMountType().equals(weaponType);
        return isType || isMountType;
    }

    @Override
    public String toString() {
        return weaponType.getDisplayName();
    }
}
