package stelnet.storage.button;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;

import stelnet.filter.cargostack.IsNotMountSize;

public class WeaponMountSize extends Button {

    public WeaponMountSize(WeaponSize weaponSize) {
        super(weaponSize.getDisplayName() + " Mount", new IsNotMountSize(weaponSize));
    }
}
