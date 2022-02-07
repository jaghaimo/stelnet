package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class ShipHullIsManufacturer extends ShipHullFilter {

    private final String manufacturer;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return manufacturer.equalsIgnoreCase(shipHull.getManufacturer());
    }

    @Override
    public String toString() {
        return manufacturer;
    }
}
