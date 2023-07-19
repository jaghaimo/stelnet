package stelnet.board.contact.fleetdata;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CargoFleetData {

    protected final CargoAPI cargo;
    protected final FleetDataAPI fleet;
    protected final List<CargoStackAPI> cargoStacks;
    protected final List<FleetMemberAPI> fleetMembers;

    public CargoFleetData(final CampaignFleetAPI fleet) {
        this(fleet.getCargo(), fleet.getFleetData());
    }

    public CargoFleetData(final CargoAPI cargo, final FleetDataAPI fleet) {
        this(cargo, fleet, cargo.getStacksCopy(), fleet.getMembersListCopy());
    }

    public CargoFleetData(final SubmarketAPI submarket) {
        this(submarket.getCargo(), submarket.getCargo().getMothballedShips());
    }

    public void add(final CargoFleetData other) {
        addCargo(other.getCargo().getStacksCopy());
        addFleet(other.getFleet().getMembersListCopy());
    }

    private void addCargo(final List<CargoStackAPI> stacks) {
        for (final CargoStackAPI stack : stacks) {
            cargo.addFromStack(stack);
        }
    }

    private void addFleet(final List<FleetMemberAPI> members) {
        for (final FleetMemberAPI member : members) {
            fleet.addFleetMember(member);
        }
    }

    public void restore() {
        clear();
        addCargo(cargoStacks);
        addFleet(fleetMembers);
    }

    public void clear() {
        cargo.clear();
        fleet.clear();
    }
}
