package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class ShowInCodex extends Filter {

    @Override
    public boolean accept(ShipHullSpecAPI object) {
        return !object.hasTag("HIDE_IN_CODEX");
    }

    @Override
    public boolean accept(WeaponSpecAPI object) {
        return !object.hasTag("HIDE_IN_CODEX");
    }
}
