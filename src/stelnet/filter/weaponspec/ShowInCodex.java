package stelnet.filter.weaponspec;

import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class ShowInCodex implements WeaponSpecFilter {

    @Override
    public boolean accept(WeaponSpecAPI object) {
        return !object.hasTag("HIDE_IN_CODEX");
    }
}
