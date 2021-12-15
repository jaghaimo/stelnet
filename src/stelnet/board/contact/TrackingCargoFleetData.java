package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.LinkedList;
import java.util.List;

public class TrackingCargoFleetData extends CargoFleetData {

    public TrackingCargoFleetData(CargoFleetData currentContent, CargoFleetData newContent) {
        super(
            currentContent.getCargo(),
            currentContent.getFleet(),
            newContent.getCargo().getStacksCopy(),
            newContent.getFleet().getMembersListCopy()
        );
    }

    @Override
    public void add(CargoFleetData other) {
        cargoStacks.addAll(other.getCargo().getStacksCopy());
        fleetMembers.addAll(other.getFleet().getMembersListCopy());
    }

    public List<CargoStackAPI> getNewContentInCargo() {
        List<CargoStackAPI> cargoStacksCopy = new LinkedList<>();
        for (CargoStackAPI stack : cargoStacks) {
            if (cargo.getStacksCopy().contains(stack)) {
                cargoStacksCopy.add(stack);
            }
        }
        return cargoStacksCopy;
    }

    public List<FleetMemberAPI> getNewContentInFleet() {
        List<FleetMemberAPI> fleetMembersCopy = new LinkedList<>();
        for (FleetMemberAPI member : fleetMembers) {
            if (fleet.getMembersListCopy().contains(member)) {
                fleetMembersCopy.add(member);
            }
        }
        return fleetMembersCopy;
    }

    public boolean hasAny() {
        return hasAnyInCargo() || hasAnyInFleet();
    }

    public boolean hasAnyInCargo() {
        return !getNewContentInCargo().isEmpty();
    }

    public boolean hasAnyInFleet() {
        return !getNewContentInFleet().isEmpty();
    }
}
