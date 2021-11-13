package stelnet.board.query.view.result;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.Getter;
import stelnet.board.query.ResultIntel;
import uilib.RenderableComponent;
import uilib.property.Size;

@Getter
public class ControlButtons extends RenderableComponent {

    private final MarketAPI market;
    private final ResultIntel intel;

    public ControlButtons(MarketAPI market, ResultIntel intel, float width) {
        this.market = market;
        this.intel = intel;
        setSize(new Size(width, 24));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        UIComponentAPI header = tooltip.getPrev();
        new ShowOnMapButton(intel, market).render(tooltip);
        UIComponentAPI showOnMapComponent = tooltip.getPrev();
        showOnMapComponent.getPosition().setYAlignOffset(header.getPosition().getHeight());
        new FullViewButton(market).render(tooltip);
        PositionAPI fullViewPosition = tooltip.getPrev().getPosition();
        fullViewPosition.rightOfTop(header, 0);
        fullViewPosition.setXAlignOffset(-fullViewPosition.getWidth());
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().belowLeft(showOnMapComponent, 0);
    }
}
