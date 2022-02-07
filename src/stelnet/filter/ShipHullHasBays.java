package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.util.L10n;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ShipHullHasBays extends ShipHullFilter {

    private final int numberOfBays;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        int fighterBays = shipHull.getFighterBays();
        if (numberOfBays > 0) {
            return fighterBays >= numberOfBays;
        }
        return fighterBays == numberOfBays;
    }

    @Override
    public String toString() {
        return L10n.get(CommonL10n.FILTER_NUMBER_OF_BAYS, numberOfBays);
    }
}
