package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

/**
 * Basic rendering logic for components {@link CustomPanelAPI}. Elements still
 * need to implement their own rendering logic using {@link TooltipMakerAPI}).
 * <p>
 * Can account for {@link Location} (TOP_LEFT by default) and {@link Position}
 * offset (0, 0 by default). Comes with a scroller (by default) if needs to use
 * larger screen than available in {@link CustomPanelAPI} panel.
 */
@Getter
@Setter
public abstract class RenderableComponent implements Renderable {

    private Location location = Location.TOP_LEFT;
    private Position offset = new Position(0, 0);
    private boolean withScroller = true;
    private Size size;

    @Override
    public void render(final CustomPanelAPI panel, final float x, final float y) {
        final Size size = getSize();
        final TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeight(), withScroller);
        render(inner);
        final PositionAPI element = panel.addUIElement(inner);
        location.render(element, x + offset.getX(), y + offset.getY());
    }

    protected void addSectionTitle(
        final TooltipMakerAPI tooltip,
        final String sectionTitle,
        final Color color,
        final float width
    ) {
        tooltip.setParaFontOrbitron();
        tooltip.addPara(sectionTitle, color, 0);
        tooltip.setParaFontDefault();
        tooltip.addButton("", "", color, color, width, 0, 0);
    }

    protected void setOffsetOfLast(final TooltipMakerAPI tooltip, final float offset) {
        tooltip.getPrev().getPosition().setXAlignOffset(offset);
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setXAlignOffset(-offset);
    }
}
