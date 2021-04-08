package stelnet.helper;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class FleetMembersHelper {

    public static int calculateShipQuantity(List<FleetMemberAPI> fleet) {
        return fleet.size();
    }
}
