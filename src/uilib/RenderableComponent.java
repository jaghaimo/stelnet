package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.Getter;
import lombok.Setter;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

/**
 * Basic rendering logic for components {@link CustomPanelAPI}. Elements still
 * need to implement their own rendering logic using {@link TooltipMakerAPI}).
 *
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
    public void render(CustomPanelAPI panel, float x, float y) {
        Size size = getSize();
        TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeight(), withScroller);
        render(inner);
        PositionAPI element = panel.addUIElement(inner);
        location.render(element, x + offset.getX(), y + offset.getY());
    }

    protected void addSectionTitle(TooltipMakerAPI tooltip, String sectionTitle, Color color, float width) {
        tooltip.setParaFontOrbitron();
        tooltip.addPara(sectionTitle, color, 0);
        tooltip.setParaFontDefault();
        tooltip.addButton("", "", color, color, width, 0, 0);
    }

    protected void setOffsetOfLast(TooltipMakerAPI tooltip, float offset) {
        tooltip.getPrev().getPosition().setXAlignOffset(offset);
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setXAlignOffset(-offset);
    }
}
