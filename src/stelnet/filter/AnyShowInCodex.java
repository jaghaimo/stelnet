package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class AnyShowInCodex extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof FighterWingSpecAPI) {
            return acceptFighterWing((FighterWingSpecAPI) object);
        }
        if (object instanceof HullModSpecAPI) {
            return acceptHullMod((HullModSpecAPI) object);
        }
        if (object instanceof ShipHullSpecAPI) {
            return acceptShipHull((ShipHullSpecAPI) object);
        }
        if (object instanceof WeaponSpecAPI) {
            return acceptWeapon((WeaponSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptFighterWing(FighterWingSpecAPI fighterWing) {
        boolean hideInCodex = fighterWing.hasTag("HIDE_IN_CODEX");
        boolean noSell = fighterWing.hasTag("no_sell");
        return !hideInCodex && !noSell;
    }

    protected boolean acceptHullMod(HullModSpecAPI hullMod) {
        return !hullMod.isHidden();
    }

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return !shipHull.getHints().contains(ShipHullSpecAPI.ShipTypeHints.HIDE_IN_CODEX);
    }

    protected boolean acceptWeapon(WeaponSpecAPI weapon) {
        boolean isNoDrop = weapon.hasTag("no_drop");
        boolean isOmega = weapon.hasTag("omega");
        boolean isRestricted = weapon.hasTag("restricted");
        return !isNoDrop && !isOmega && !isRestricted;
    }
}