package stelnet.filter;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class WeaponSlotIsSizeType extends WeaponSlotFilter {

    private final Filter weaponSize;
    private final Filter weaponType;

    public WeaponSlotIsSizeType(WeaponSize weaponSize, WeaponType weaponType) {
        this(new WeaponSlotIsSize(weaponSize), new WeaponSlotIsType(weaponType));
    }

    @Override
    protected boolean acceptWeaponSlot(WeaponSlotAPI weaponSlot) {
        return weaponSize.accept(weaponSlot) && weaponType.accept(weaponSlot);
    }

    @Override
    public String toString() {
        return weaponSize + " " + weaponType;
    }
}
