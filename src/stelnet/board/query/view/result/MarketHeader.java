package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.Getter;
import stelnet.board.query.ResultIntel;
import uilib.Heading;
import uilib.RenderableComponent;

@Getter
public class MarketHeader extends RenderableComponent {

    private final Heading heading;
    private final ShowOnMapButton showOnMapButton;
    private final FullViewButton fullViewButton;

    public MarketHeader(MarketAPI market, ResultIntel intel) {
        FactionAPI faction = market.getFaction();
        heading = new Heading(" " + market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
        heading.setAlignment(Alignment.LMID);
        showOnMapButton = new ShowOnMapButton(intel, market);
        showOnMapButton.setTextColor(faction.getBrightUIColor());
        showOnMapButton.setBackgroundColor(faction.getGridUIColor());
        fullViewButton = new FullViewButton(market);
        fullViewButton.setTextColor(faction.getBrightUIColor());
        fullViewButton.setBackgroundColor(faction.getGridUIColor());
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        UIComponentAPI headingComponent = renderHeading(tooltip);
        renderButtons(tooltip, headingComponent);
        resetTooltip(tooltip, headingComponent);
    }

    private UIComponentAPI renderHeading(TooltipMakerAPI tooltip) {
        heading.render(tooltip);
        return tooltip.getPrev();
    }

    private void renderButtons(TooltipMakerAPI tooltip, UIComponentAPI headingComponent) {
        tooltip.setButtonFontVictor10();
        showOnMapButton.render(tooltip);
        PositionAPI showOnMapPosition = tooltip.getPrev().getPosition();
        showOnMapPosition.rightOfTop(headingComponent, 0);
        showOnMapPosition.setXAlignOffset(-showOnMapPosition.getWidth() + 5);
        fullViewButton.render(tooltip);
        PositionAPI fullViewPosition = tooltip.getPrev().getPosition();
        fullViewPosition.rightOfTop(headingComponent, 0);
        fullViewPosition.setXAlignOffset(-fullViewPosition.getWidth() + -showOnMapPosition.getWidth() + 3);
    }

    private void resetTooltip(TooltipMakerAPI tooltip, UIComponentAPI headingComponent) {
        tooltip.setButtonFontDefault();
        tooltip.addSpacer(0);
        PositionAPI spacerReset = tooltip.getPrev().getPosition();
        spacerReset.belowLeft(headingComponent, 0);
    }
}
