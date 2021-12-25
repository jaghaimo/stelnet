package stelnet.board.contact;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.SectorUtils;

public class ContactDialog extends RuleBasedInteractionDialogPluginImpl {

    private InteractionDialogAPI dialog;
    private final MarketAPI market;
    private final PersonAPI person;
    private final IntelUIAPI ui;
    private final CargoFleetData playerData;
    private final CargoFleetData storageData;

    public ContactDialog(IntelUIAPI ui, PersonAPI person, SubmarketAPI storage) {
        super("OpenCDE");
        this.person = person;
        this.market = storage.getMarket();
        this.ui = ui;
        playerData = new CargoFleetData(SectorUtils.getPlayerFleet());
        playerData.clear();
        storageData = new CargoFleetData(storage);
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        SectorEntityToken token = dialog.getInteractionTarget();
        token.setActivePerson(person);
        super.init(dialog);
        this.dialog = dialog;
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
        ContactsBoard board = ContactsBoard.getInstance(ContactsBoard.class);
        board.getRenderableState().addTrackingData(market, storageData, playerData);
        storageData.add(playerData);
        playerData.restore();
        dialog.dismiss();
        ui.recreateIntelUI();
        ui.updateUIForItem(board);
    }
}
