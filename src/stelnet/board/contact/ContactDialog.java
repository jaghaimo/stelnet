package stelnet.board.contact;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import stelnet.util.SectorUtils;

public class ContactDialog extends RuleBasedInteractionDialogPluginImpl {

    private final PersonAPI person;
    private final CargoFleetData playerData;
    private final CargoFleetData storageData;

    public ContactDialog(PersonAPI person) {
        super("OpenCDE");
        this.person = person;
        SubmarketAPI storage = person.getMarket().getSubmarket(Submarkets.SUBMARKET_STORAGE);
        this.playerData = new CargoFleetData(SectorUtils.getPlayerFleet());
        this.storageData = new CargoFleetData(storage);
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        SectorEntityToken token = dialog.getInteractionTarget();
        token.setActivePerson(person);
        super.init(dialog);
        playerData.clear();
    }

    @Override
    public void optionSelected(String text, Object optionData) {
        if (optionData.equals("cutCommLink")) {
            optionData = FAILSAFE_LEAVE;
        }
        if (optionData.equals(FAILSAFE_LEAVE)) {
            storageData.add(playerData);
            playerData.restore();
        }
        super.optionSelected(text, optionData);
    }
}
