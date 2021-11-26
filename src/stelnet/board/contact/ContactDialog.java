package stelnet.board.contact;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;

public class ContactDialog extends RuleBasedInteractionDialogPluginImpl {

    private final PersonAPI person;

    public ContactDialog(PersonAPI person) {
        super("OpenCDE");
        this.person = person;
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        SectorEntityToken token = dialog.getInteractionTarget();
        token.setActivePerson(person);
        super.init(dialog);
    }

    @Override
    public void optionSelected(String text, Object optionData) {
        if (optionData.equals("cutCommLink")) {
            optionData = FAILSAFE_LEAVE;
        }
        super.optionSelected(text, optionData);
    }
}
