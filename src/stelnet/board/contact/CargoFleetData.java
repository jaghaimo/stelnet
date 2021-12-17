package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CargoFleetData {

    protected final CargoAPI cargo;
    protected final FleetDataAPI fleet;
    protected final List<CargoStackAPI> cargoStacks;
    protected final List<FleetMemberAPI> fleetMembers;

    public CargoFleetData(CargoAPI cargo, FleetDataAPI fleet) {
        this(cargo, fleet, cargo.getStacksCopy(), fleet.getMembersListCopy());
    }

    public CargoFleetData(CampaignFleetAPI fleet) {
        this(fleet.getCargo(), fleet.getFleetData());
    }

    public CargoFleetData(SubmarketAPI submarket) {
        this(submarket.getCargo(), submarket.getCargo().getMothballedShips());
    }

    public void add(CargoFleetData other) {
        addCargo(other.getCargo().getStacksCopy());
        addFleet(other.getFleet().getMembersListCopy());
    }

    public void clear() {
        cargo.clear();
        fleet.clear();
    }

    public void restore() {
        clear();
        addCargo(cargoStacks);
        addFleet(fleetMembers);
    }

    private void addCargo(List<CargoStackAPI> stacks) {
        for (CargoStackAPI stack : stacks) {
            cargo.addFromStack(stack);
        }
    }

    private void addFleet(List<FleetMemberAPI> members) {
        for (FleetMemberAPI member : members) {
            fleet.addFleetMember(member);
        }
    }
}
