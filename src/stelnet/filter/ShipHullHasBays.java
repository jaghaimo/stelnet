package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ShipHullHasBays extends ShipHullFilter {

    private final int numberOfBays;

    @Override
    public String toString() {
        return L10n.common("FILTER_NUMBER_OF_BAYS", numberOfBays);
    }

    protected boolean acceptShipHull(final ShipHullSpecAPI shipHull) {
        final int fighterBays = shipHull.getFighterBays();
        if (numberOfBays > 0) {
            return fighterBays >= numberOfBays;
        }
        return fighterBays == numberOfBays;
    }
}
