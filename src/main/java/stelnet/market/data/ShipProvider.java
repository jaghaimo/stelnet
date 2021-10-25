package stelnet.market.data;

import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.filter.shiphullspec.ShowInCodex;
import stelnet.util.CollectionReducer;
import stelnet.util.Settings;

// TODO : Find a better place for this class
public class ShipProvider {

    public List<FleetMemberAPI> getShips() {
        return getShips(Collections.<FleetMemberFilter>emptyList());
    }

    public List<FleetMemberAPI> getShips(List<FleetMemberFilter> filters) {
        List<ShipHullSpecAPI> allShipHullSpecs = Settings.getAllShipHullSpecs();
        CollectionReducer.reduce(allShipHullSpecs, new ShowInCodex());
        return convertToFleetMembers(allShipHullSpecs, filters);
    }

    private List<FleetMemberAPI> convertToFleetMembers(
        List<ShipHullSpecAPI> shipHullSpecs,
        List<FleetMemberFilter> filters
    ) {
        List<FleetMemberAPI> members = new LinkedList<>();
        for (ShipHullSpecAPI shipHullSpec : shipHullSpecs) {
            members.add(makeFleetMember(shipHullSpec));
        }
        CollectionReducer.reduce(members, filters);
        return members;
    }

    private FleetMemberAPI makeFleetMember(ShipHullSpecAPI shipHullSpec) {
        // TODO : Implement this
        return null;
    }
}
