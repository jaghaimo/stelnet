package stelnet.board.market.view;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignEntityPickerListener;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import stelnet.board.market.ViewerBoard;
import stelnet.util.L10n;
import stelnet.view.market.InMarketStrategy;

@RequiredArgsConstructor
public class MarketSelectDialog implements InteractionDialogPlugin {

    private final IntelUIAPI ui;
    private final List<SectorEntityToken> entities;

    @Override
    public void init(final InteractionDialogAPI dialog) {
        final ViewerBoard board = ViewerBoard.getInstance();
        dialog.showCampaignEntityPicker(
            L10n.get("marketViewDialogSelect"),
            L10n.get("marketViewDialogSelected"),
            L10n.get("marketViewDialogConfirm"),
            Global.getSector().getPlayerFaction(),
            entities,
            new CampaignEntityPickerListener() {
                @Override
                public String getMenuItemNameOverrideFor(SectorEntityToken entity) {
                    return null;
                }

                @Override
                public void pickedEntity(SectorEntityToken entity) {
                    board.getState().setMarketProvider(new InMarketStrategy(entity.getMarket()));
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
                    return L10n.get(
                        "marketViewDialogSelection",
                        entity.getName(),
                        entity.getContainingLocation().getNameWithTypeShort()
                    );
                }

                @Override
                public void createInfoText(TooltipMakerAPI info, SectorEntityToken entity) {}

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
            }
        );
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {}

    @Override
    public void optionMousedOver(String optionText, Object optionData) {}

    @Override
    public void advance(float amount) {}

    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {}

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }
}
