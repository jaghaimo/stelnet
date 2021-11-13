package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
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
        this.heading = new Heading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor());
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
        UIComponentAPI header = tooltip.getPrev();
        showOnMapButton.render(tooltip);
        UIComponentAPI showOnMapComponent = tooltip.getPrev();
        showOnMapComponent.getPosition().setYAlignOffset(header.getPosition().getHeight());
        showOnMapComponent.getPosition().setXAlignOffset(-5);
        fullViewButton.render(tooltip);
        PositionAPI fullViewPosition = tooltip.getPrev().getPosition();
        fullViewPosition.rightOfTop(header, 0);
        fullViewPosition.setXAlignOffset(-fullViewPosition.getWidth() + 5);
        tooltip.addSpacer(0);
        PositionAPI spacerReset = tooltip.getPrev().getPosition();
        spacerReset.belowLeft(showOnMapComponent, 0);
        spacerReset.setXAlignOffset(4);
    }
}
