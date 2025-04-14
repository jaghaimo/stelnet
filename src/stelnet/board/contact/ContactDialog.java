package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;
import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.extern.log4j.Log4j;
import stelnet.util.MemoryHelper;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;

@Log4j
public class ContactDialog extends RuleBasedInteractionDialogPluginImpl {

    private InteractionDialogAPI dialog;
    private InteractionDialogAPI dialog2;
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
        playerData = new CargoFleetData(Global.getSector().getPlayerFleet());
        storageData = new CargoFleetData(storage);
        if (this.isRemoteCall()) {
            playerData.clear();
        }
    }

    @Override
    public void init(InteractionDialogAPI dialog) {
        SectorEntityToken token = dialog.getInteractionTarget();
        token.setActivePerson(person);
        this.dialog2 = dialog;  // Use another variable to log when problem happens.
        super.init(dialog);
        this.dialog = dialog;
    }

    @Override
    public void notifyActivePersonChanged() {
        if (dialog != null) {
            dialog.hideVisualPanel();
        } else {
            log.warn("Dialog is not set, fallback and call dialog2.hideVisualPanel. For more info see GH issue #119.");
            //dialog2.hideVisualPanel();  // not needed?
        }
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

    private boolean isRemoteCall() {
        SectorEntityToken playerFocus = Global.getSector().getPlayerFleet().getOrbitFocus();
        if (playerFocus == null) {
            return true;
        }
        if (playerFocus.equals(this.market.getPrimaryEntity())) {
            return false;
        }
        for (SectorEntityToken connectedEntity : this.market.getConnectedEntities()) {
            if (playerFocus.equals(connectedEntity)) {
                return false;
            }
        }
        return true;
    }

    private void dismiss() {
        MemoryHelper.unset(ModConstants.MEMORY_IS_CALLING);
        ContactsBoard board = StelnetHelper.getInstance(ContactsBoard.class);
        if (this.isRemoteCall()) {
            board.getRenderableState().addTrackingData(market, storageData, playerData);
            storageData.add(playerData);
            playerData.restore();
        }
        if (this.dialog != null) {
            dialog.dismiss();
        } else {
            log.warn("Dialog is not set, fallback and call dialog2.dismiss. For more info see GH issue #119.");
            dialog2.dismiss();
        }
        ui.recreateIntelUI();
        ui.updateUIForItem(board);
    }
}
