package stelnet.board.contact;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;
import stelnet.util.SectorUtils;

public class ContactDialog extends RuleBasedInteractionDialogPluginImpl {

    private InteractionDialogAPI dialog;
    private final PersonAPI person;
    private final CargoFleetData playerData;
    private final CargoFleetData storageData;

    public ContactDialog(PersonAPI person, SubmarketAPI storage) {
        super("OpenCDE");
        this.person = person;
        this.playerData = new CargoFleetData(SectorUtils.getPlayerFleet());
        this.storageData = new CargoFleetData(storage);
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        SectorEntityToken token = dialog.getInteractionTarget();
        token.setActivePerson(person);
        super.init(dialog);
        this.dialog = dialog;
        playerData.clear();
    }

    @Override
    public void notifyActivePersonChanged() {
        dialog.hideVisualPanel();
        super.notifyActivePersonChanged();
        dismiss();
    }

    @Override
    public void optionSelected(String text, Object optionData) {
        if (optionData.equals("cutCommLink")) {
            dismiss();
            return;
        }
        super.optionSelected(text, optionData);
    }

    private void dismiss() {
        storageData.add(playerData);
        playerData.restore();
        dialog.dismiss();
    }
}
