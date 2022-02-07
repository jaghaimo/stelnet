package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.Getter;
import stelnet.BaseIntel;
import uilib.Button;
import uilib.Heading;
import uilib.RenderableComponent;

@Getter
public class MarketHeader extends RenderableComponent {

    private final Heading heading;
    private final GoButton goButton;
    private final ShowButton showButton;
    private final PeekButton peekButton;

    public MarketHeader(MarketAPI market, BaseIntel intel) {
        FactionAPI faction = market.getFaction();
        heading = new Heading(" " + market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
        heading.setAlignment(Alignment.LMID);
        goButton = new GoButton(market);
        goButton.setTextColor(faction.getBrightUIColor());
        goButton.setBackgroundColor(faction.getGridUIColor());
        peekButton = new PeekButton(market);
        peekButton.setTextColor(faction.getBrightUIColor());
        peekButton.setBackgroundColor(faction.getGridUIColor());
        showButton = new ShowButton(intel, market);
        showButton.setTextColor(faction.getBrightUIColor());
        showButton.setBackgroundColor(faction.getGridUIColor());
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
        UIComponentAPI showComponent = renderFirstButton(showButton, tooltip, headingComponent);
        UIComponentAPI peekComponent = renderNextButton(peekButton, tooltip, showComponent);
        renderNextButton(goButton, tooltip, peekComponent);
    }

    private void resetTooltip(TooltipMakerAPI tooltip, UIComponentAPI headingComponent) {
        tooltip.setButtonFontDefault();
        tooltip.addSpacer(0);
        PositionAPI spacerReset = tooltip.getPrev().getPosition();
        spacerReset.belowLeft(headingComponent, 0);
    }

    private UIComponentAPI renderFirstButton(Button button, TooltipMakerAPI tooltip, UIComponentAPI sibling) {
        button.render(tooltip);
        UIComponentAPI component = tooltip.getPrev();
        PositionAPI position = component.getPosition();
        position.rightOfMid(sibling, -position.getWidth() + 5);
        return component;
    }

    private UIComponentAPI renderNextButton(Button button, TooltipMakerAPI tooltip, UIComponentAPI sibling) {
        button.render(tooltip);
        UIComponentAPI component = tooltip.getPrev();
        PositionAPI position = component.getPosition();
        position.leftOfMid(sibling, 1);
        return component;
    }
}
