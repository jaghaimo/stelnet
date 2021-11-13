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
    private final MapButton mapButton;
    private final ViewerButton viewerButton;

    public MarketHeader(MarketAPI market, ResultIntel intel) {
        FactionAPI faction = market.getFaction();
        heading = new Heading(" " + market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
        heading.setAlignment(Alignment.LMID);
        mapButton = new MapButton(intel, market);
        mapButton.setTextColor(faction.getBrightUIColor());
        mapButton.setBackgroundColor(faction.getGridUIColor());
        viewerButton = new ViewerButton(market);
        viewerButton.setTextColor(faction.getBrightUIColor());
        viewerButton.setBackgroundColor(faction.getGridUIColor());
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
        mapButton.render(tooltip);
        PositionAPI mapPosition = tooltip.getPrev().getPosition();
        mapPosition.rightOfTop(headingComponent, 0);
        mapPosition.setXAlignOffset(-mapPosition.getWidth() + 5);
        viewerButton.render(tooltip);
        PositionAPI viewerPosition = tooltip.getPrev().getPosition();
        viewerPosition.rightOfTop(headingComponent, 0);
        viewerPosition.setXAlignOffset(-viewerPosition.getWidth() + -mapPosition.getWidth() + 3);
    }

    private void resetTooltip(TooltipMakerAPI tooltip, UIComponentAPI headingComponent) {
        tooltip.setButtonFontDefault();
        tooltip.addSpacer(0);
        PositionAPI spacerReset = tooltip.getPrev().getPosition();
        spacerReset.belowLeft(headingComponent, 0);
    }
}
