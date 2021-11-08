package stelnet.filter;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.loading.WeaponSlotAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeaponSlotIsSize extends WeaponSlotFilter {

    private final WeaponSize weaponSize;

    @Override
    protected boolean acceptWeaponSlot(WeaponSlotAPI weaponSlot) {
        return weaponSlot.getSlotSize().equals(weaponSize);
    }
}
