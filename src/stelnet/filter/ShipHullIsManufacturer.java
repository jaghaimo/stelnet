package stelnet.filter;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShipHullIsManufacturer extends ShipHullFilter {

    private final String manufacturer;

    protected boolean acceptShipHull(ShipHullSpecAPI shipHull) {
        return manufacturer.equalsIgnoreCase(shipHull.getManufacturer());
    }
}
