package stelnet.storage.data;

import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class StorageData {

    private SubmarketAPI submarket;
    private CargoAPI items;
    private List<FleetMemberAPI> ships;

    public StorageData(SubmarketAPI submarket, CargoAPI items, List<FleetMemberAPI> ships) {
        this.submarket = submarket;
        this.items = items;
        this.ships = ships;
    }

    public SubmarketAPI getSubmarket() {
        return submarket;
    }

    public CargoAPI getItems() {
        return items;
    }

    public List<FleetMemberAPI> getShips() {
        return ships;
    }
}
