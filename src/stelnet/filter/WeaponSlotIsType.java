package stelnet.filter;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class WeaponSlotIsType extends WeaponSlotFilter {

    private final WeaponType weaponType;

    @Override
    protected boolean acceptWeaponSlot(WeaponSlotAPI weaponSlot) {
        if (weaponSlot.isHidden()) {
            return false;
        }
        return weaponSlot.getWeaponType().equals(weaponType);
    }

    @Override
    public String toString() {
        return weaponType.getDisplayName();
    }
}
