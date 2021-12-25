package stelnet.filter;

import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class WeaponIsDamage extends WeaponFilter {

    private final DamageType damageType;

    @Override
    protected boolean acceptWeapon(WeaponSpecAPI weapon) {
        return weapon.getDamageType().equals(damageType);
    }

    @Override
    public String toString() {
        return damageType.getDisplayName();
    }
}
