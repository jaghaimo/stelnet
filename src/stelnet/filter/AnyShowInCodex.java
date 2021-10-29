package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class AnyShowInCodex extends Filter {

    @Override
    public boolean accept(FighterWingSpecAPI fighterWing) {
        boolean hideInCodex = fighterWing.hasTag("HIDE_IN_CODEX");
        boolean noSell = fighterWing.hasTag("no_sell");
        return !hideInCodex && !noSell;
    }

    @Override
    public boolean accept(HullModSpecAPI hullMod) {
        return !hullMod.isHidden();
    }

    @Override
    public boolean accept(ShipHullSpecAPI shipHull) {
        return !shipHull.hasTag("HIDE_IN_CODEX");
    }

    @Override
    public boolean accept(WeaponSpecAPI weapon) {
        boolean isNoDrop = weapon.hasTag("no_drop");
        boolean isOmega = weapon.hasTag("omega");
        boolean isRestricted = weapon.hasTag("restricted");
        return !isNoDrop && !isOmega && !isRestricted;
    }
}
