package stelnet.board.viewer;

import com.fs.starfarer.api.campaign.CampaignEntityPickerListener;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import stelnet.widget.viewer.InMarketStrategy;

@RequiredArgsConstructor
public class MarketSelectPicker implements CampaignEntityPickerListener {

    private final ViewerBoard board = ViewerBoard.getInstance(ViewerBoard.class);
    private final InteractionDialogAPI dialog;
    private final IntelUIAPI ui;

    @Override
    public String getMenuItemNameOverrideFor(SectorEntityToken entity) {
        return null;
    }

    @Override
    public void pickedEntity(SectorEntityToken entity) {
        board.getState().setDisplayStrategy(new InMarketStrategy(entity.getMarket()));
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
