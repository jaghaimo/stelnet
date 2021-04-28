package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.Data;
import stelnet.helper.LogHelper;

/**
 * Base building block of UI (both {@link CustomPanelAPI} and
 * {@link TooltipMakerAPI}).
 *
 * Provides a skeletal implementation of the Renderable interface.
 *
 * Can account for {@link Location} (TOP_LEFT by default) and {@link Position}
 * offset (0, 0 by default). Comes with a scroller (by default) if needs to use
 * larger screen than available in {@link CustomPanelAPI} panel.
 */
@Data
public abstract class AbstractRenderable implements Renderable {

    private Location location = Location.TOP_LEFT;
    private Position offset = new Position(0, 0);
    private boolean withScroller = true;
    private Size size;

    @Override
    public void render(CustomPanelAPI panel) {
        Size size = getSize();
        TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeight(), withScroller);
        render(inner);
        PositionAPI element = panel.addUIElement(inner);
        location.render(element, offset.getX(), offset.getY());
        log(offset);
    }

    @Deprecated
    public void render(CustomPanelAPI panel, float x, float y) {
        setOffset(new Position(offset.getX() + x, offset.getY() + y));
        render(panel);
    }

    public void log(Position position) {
        LogHelper.debug(String.format("Rendered %s in %s", this, position));
    }
}
