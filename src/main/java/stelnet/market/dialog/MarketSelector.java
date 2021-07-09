package stelnet.market.dialog;

import java.util.Map;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignEntityPickerListener;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.RequiredArgsConstructor;
import stelnet.helper.MarketHelper;
import stelnet.market.MarketViewBoard;

@RequiredArgsConstructor
public class MarketSelector implements InteractionDialogPlugin {

    private final IntelUIAPI ui;

    @Override
    public void init(final InteractionDialogAPI dialog) {
        final MarketViewBoard board = MarketViewBoard.getInstance();
        dialog.showCampaignEntityPicker(
                "Select a market",
                "Selected: ",
                "Confirm",
                Global.getSector().getPlayerFaction(),
                MarketHelper.convertMarkets(MarketHelper.getMarkets()),
                new CampaignEntityPickerListener() {

                    @Override
                    public String getMenuItemNameOverrideFor(SectorEntityToken entity) {
                        return null;
                    }

                    @Override
                    public void pickedEntity(SectorEntityToken entity) {
                        board.setMarket(entity.getMarket());
                        ui.updateUIForItem(board);
                        dialog.dismiss();
                    }

                    @Override
                    public void cancelledEntityPicking() {
                        ui.updateUIForItem(board);
                        dialog.dismiss();
                    }

                    @Override
                    public String getSelectedTextOverrideFor(SectorEntityToken entity) {
                        return entity.getName() + " in " + entity.getContainingLocation().getNameWithTypeShort();
                    }

                    @Override
                    public void createInfoText(TooltipMakerAPI info, SectorEntityToken entity) {
                    }

                    @Override
                    public boolean canConfirmSelection(SectorEntityToken entity) {
                        return true;
                    }

                    @Override
                    public float getFuelColorAlphaMult() {
                        return 0;
                    }

                    @Override
                    public float getFuelRangeMult() {
                        return 0;
                    }
                });
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
    }

    @Override
    public void optionMousedOver(String optionText, Object optionData) {
    }

    @Override
    public void advance(float amount) {
    }

    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {
    }

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }
}
