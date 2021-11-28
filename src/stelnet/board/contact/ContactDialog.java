package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FleetDataAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import java.util.List;
import stelnet.util.SectorUtils;

public class ContactDialog extends RuleBasedInteractionDialogPluginImpl {

    private final CampaignFleetAPI fleet = SectorUtils.getPlayerFleet();
    private final FleetDataAPI fleetData = fleet.getFleetData();
    private final List<FleetMemberAPI> fleetSnapshot = fleetData.getMembersListCopy();
    private final PersonAPI person;
    private final SubmarketAPI storage;

    public ContactDialog(PersonAPI person) {
        super("OpenCDE");
        this.person = person;
        this.storage = person.getMarket().getSubmarket(Submarkets.SUBMARKET_STORAGE);
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        SectorEntityToken token = dialog.getInteractionTarget();
        token.setActivePerson(person);
        super.init(dialog);
        swapCargo();
    }

    @Override
    public void optionSelected(String text, Object optionData) {
        if (optionData.equals("cutCommLink")) {
            optionData = FAILSAFE_LEAVE;
            swapCargo();
            transferMembers();
        }
        super.optionSelected(text, optionData);
    }

    private void swapCargo() {
        CargoAPI storageCargo = storage.getCargo();
        CargoAPI storageCargoCopy = storageCargo.createCopy();
        CargoAPI playerCargo = fleet.getCargo();
        CargoAPI playerCargoCopy = playerCargo.createCopy();
        swapCargo(storageCargo, playerCargoCopy);
        swapCargo(playerCargo, storageCargoCopy);
    }

    private void swapCargo(CargoAPI receiving, CargoAPI donating) {
        receiving.clear();
        receiving.addAll(donating, false);
    }

    private void transferMembers() {
        List<FleetMemberAPI> currentFleet = fleetData.getMembersListCopy();
        currentFleet.removeAll(fleetSnapshot);
        FleetDataAPI mothballedShips = storage.getCargo().getMothballedShips();
        for (FleetMemberAPI member : currentFleet) {
            fleetData.removeFleetMember(member);
            mothballedShips.addFleetMember(member);
        }
    }
}
