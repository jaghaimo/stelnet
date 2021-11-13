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

    public MarketHeader(MarketAPI market, ResultIntel intel, float width) {
        FactionAPI faction = market.getFaction();
        this.heading = new Heading(" " + market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
        this.heading.setAlignment(Alignment.LMID);
        this.showOnMapButton = new ShowOnMapButton(intel, market);
        this.showOnMapButton.setTextColor(faction.getBrightUIColor());
        this.showOnMapButton.setBackgroundColor(faction.getGridUIColor());
        this.fullViewButton = new FullViewButton(market);
        this.fullViewButton.setTextColor(faction.getBrightUIColor());
        this.fullViewButton.setBackgroundColor(faction.getGridUIColor());
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        heading.render(tooltip);
        UIComponentAPI headingComponent = tooltip.getPrev();
        tooltip.setButtonFontVictor10();
        showOnMapButton.render(tooltip);
        PositionAPI showOnMapPosition = tooltip.getPrev().getPosition();
        showOnMapPosition.rightOfTop(headingComponent, 0);
        showOnMapPosition.setXAlignOffset(-showOnMapPosition.getWidth() + 5);
        fullViewButton.render(tooltip);
        PositionAPI fullViewPosition = tooltip.getPrev().getPosition();
        fullViewPosition.rightOfTop(headingComponent, 0);
        fullViewPosition.setXAlignOffset(-fullViewPosition.getWidth() + -showOnMapPosition.getWidth() + 3);
        // reset tooltip
        tooltip.setButtonFontDefault();
        tooltip.addSpacer(0);
        PositionAPI spacerReset = tooltip.getPrev().getPosition();
        spacerReset.belowLeft(headingComponent, 0);
    }
}
