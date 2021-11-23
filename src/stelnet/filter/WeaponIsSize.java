package stelnet.filter;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeaponIsSize extends WeaponFilter {

    private final WeaponSize weaponSize;

    @Override
    protected boolean acceptWeapon(WeaponSpecAPI weapon) {
        return weapon.getSize().equals(weaponSize);
    }

    @Override
    public String toString() {
        return weaponSize.getDisplayName();
    }
}
