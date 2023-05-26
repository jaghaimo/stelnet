package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI.ShipTypeHints;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class ShipHullIsInCodex extends ShipHullFilter {

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        boolean isDamaged = shipHull.isDHull();
        boolean isHideInCodex = shipHull.getHints().contains(ShipTypeHints.HIDE_IN_CODEX);
        return !isDamaged && !isHideInCodex;
    }
}
