package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.characters.RelationshipAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.board.query.Result;
import stelnet.util.L10n;
import uilib.RenderableComponent;
import uilib.UiConstants;

@RequiredArgsConstructor
public class MarketInfo extends RenderableComponent {

    private final float width;
    private final Result result;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        FactionAPI faction = result.getMarket().getFaction();
        tooltip.addImage(faction.getLogo(), width, 128, UiConstants.DEFAULT_SPACER);
        RelationshipAPI relationship = faction.getRelToPlayer();
        String reputation = relationship.getLevel().getDisplayName().toLowerCase();
        String reputationFormat = L10n.get(CommonL10n.INTEL_OWNER_RELATIONSHIP, reputation);
        tooltip.addPara(reputationFormat, UiConstants.DEFAULT_SPACER, relationship.getRelColor(), reputation);
    }
}
