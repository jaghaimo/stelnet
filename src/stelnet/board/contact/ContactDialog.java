package stelnet.board.contact;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.RuleBasedInteractionDialogPluginImpl;
import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.contact.fleetdata.CargoFleetData;
import stelnet.util.MemoryManager;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;

public class ContactDialog extends RuleBasedInteractionDialogPluginImpl {

    private final MarketAPI market;
    private final PersonAPI person;
    private final IntelUIAPI ui;
    private final CargoFleetData playerData;
    private final CargoFleetData storageData;
    private InteractionDialogAPI dialog;

    public ContactDialog(final IntelUIAPI ui, final PersonAPI person, final SubmarketAPI storage) {
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

    private boolean isRemoteCall() {
        final SectorEntityToken playerFocus = Global.getSector().getPlayerFleet().getOrbitFocus();
        if (playerFocus == null) {
            return true;
        }
        if (playerFocus.equals(this.market.getPrimaryEntity())) {
            return false;
        }
        for (final SectorEntityToken connectedEntity : this.market.getConnectedEntities()) {
            if (playerFocus.equals(connectedEntity)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void init(final InteractionDialogAPI dialog) {
        final SectorEntityToken token = dialog.getInteractionTarget();
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
    public void optionSelected(final String text, final Object optionData) {
        if (optionData.equals("cutCommLink")) {
            dismiss();
            return;
        }
        super.optionSelected(text, optionData);
    }

    private void dismiss() {
        MemoryManager.getInstance().unset(ModConstants.MEMORY_IS_CALLING);
        final ContactBoard board = StelnetHelper.getInstance(ContactBoard.class);
        if (this.isRemoteCall()) {
            board.getModel().addTrackingData(market, storageData, playerData);
            storageData.add(playerData);
            playerData.restore();
        }
        dialog.dismiss();
        ui.recreateIntelUI();
        ui.updateUIForItem(board);
    }
}
