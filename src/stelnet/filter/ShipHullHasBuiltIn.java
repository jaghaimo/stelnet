package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullHasBuiltIn extends ShipHullFilter {

    private final HullModSpecAPI hullSpec;

    @Override
    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return shipHull.getBuiltInMods().contains(hullSpec.getId());
    }

    @Override
    public String toString() {
        return hullSpec.getDisplayName();
    }
}
