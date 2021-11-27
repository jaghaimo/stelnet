package stelnet.filter;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeaponSlotIsType extends WeaponSlotFilter {

    private final WeaponType weaponType;

    @Override
    protected boolean acceptWeaponSlot(WeaponSlotAPI weaponSlot) {
        if (weaponSlot.isSystemSlot()) {
            return true;
        }
        return weaponSlot.getWeaponType().equals(weaponType);
    }

    @Override
    public String toString() {
        return weaponType.getDisplayName();
    }
}
