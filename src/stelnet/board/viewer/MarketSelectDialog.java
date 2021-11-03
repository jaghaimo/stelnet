package stelnet.board.viewer;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;

@RequiredArgsConstructor
public class MarketSelectDialog implements InteractionDialogPlugin {

    private final IntelUIAPI ui;
    private final List<SectorEntityToken> entities;

    @Override
    public void init(final InteractionDialogAPI dialog) {
        dialog.showCampaignEntityPicker(
            L10n.get("marketViewDialogSelect"),
            L10n.get("marketViewDialogSelected"),
            L10n.get("marketViewDialogConfirm"),
            Global.getSector().getPlayerFaction(),
            entities,
            new MarketSelectPicker(dialog, ui)
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
