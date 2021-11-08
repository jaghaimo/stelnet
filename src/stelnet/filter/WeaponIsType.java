package stelnet.filter;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeaponIsType extends WeaponFilter {

    private final WeaponType weaponType;

    @Override
    protected boolean acceptWeapon(WeaponSpecAPI weapon) {
        return weapon.getType().equals(weaponType);
    }
}
