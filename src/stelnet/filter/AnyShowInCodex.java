package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;

public class AnyShowInCodex extends Filter {

    private static final String HIDE_IN_CODEX = "HIDE_IN_CODEX";

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
        boolean hideInCodex = fighterWing.hasTag(HIDE_IN_CODEX);
        boolean isNoSell = fighterWing.hasTag(Tags.NO_SELL);
        return !hideInCodex && !isNoSell;
    }

    protected boolean acceptHullMod(HullModSpecAPI hullMod) {
        return !hullMod.isHidden();
    }

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return !shipHull.getHints().contains(ShipHullSpecAPI.ShipTypeHints.HIDE_IN_CODEX);
    }

    protected boolean acceptWeapon(WeaponSpecAPI weapon) {
        boolean isNoDrop = weapon.hasTag(Tags.NO_DROP);
        boolean isNoSell = weapon.hasTag(Tags.NO_SELL);
        boolean isRestricted = weapon.hasTag(Tags.RESTRICTED);
        return !isNoDrop && !isNoSell && !isRestricted;
    }
}
