package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.Getter;
import stelnet.board.query.ResultIntel;
import uilib.RenderableComponent;

@Getter
public class ControlButtons extends RenderableComponent {

    private final ShowOnMapButton showOnMapButton;
    private final FullViewButton fullViewButton;

    public ControlButtons(MarketAPI market, ResultIntel intel, float width) {
        this.showOnMapButton = new ShowOnMapButton(intel, market);
        this.fullViewButton = new FullViewButton(market);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        UIComponentAPI header = tooltip.getPrev();
        showOnMapButton.render(tooltip);
        UIComponentAPI showOnMapComponent = tooltip.getPrev();
        showOnMapComponent.getPosition().setYAlignOffset(header.getPosition().getHeight());
        showOnMapComponent.getPosition().setXAlignOffset(-4);
        fullViewButton.render(tooltip);
        PositionAPI fullViewPosition = tooltip.getPrev().getPosition();
        fullViewPosition.rightOfTop(header, 0);
        fullViewPosition.setXAlignOffset(-fullViewPosition.getWidth() + 4);
        tooltip.addSpacer(0);
        PositionAPI spacerReset = tooltip.getPrev().getPosition();
        spacerReset.belowLeft(showOnMapComponent, 0);
        spacerReset.setXAlignOffset(4);
    }
}
