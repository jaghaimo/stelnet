package stelnet.board.query.provider;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ManufacturerProvider extends QueryProvider {

    public Set<String> getManufacturers() {
        List<ShipHullSpecAPI> allShipHullSpecs = getShipHulls();
        return extractManufacturers(allShipHullSpecs);
    }

    public Set<String> extractManufacturers(List<ShipHullSpecAPI> shipHulls) {
        Set<String> manufacturers = new TreeSet<>();
        for (ShipHullSpecAPI shipHull : shipHulls) {
            manufacturers.add(shipHull.getManufacturer());
        }
        return manufacturers;
    }
}
