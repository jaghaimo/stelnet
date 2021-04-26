package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;

/**
 * Base building block of UI (both {@link CustomPanelAPI} and
 * {@link TooltipMakerAPI}).
 *
 * Can account for {@link Location} (TOP_LEFT by default) and {@link Position}
 * offset (0, 0 by default). Comes with a scroller (by default) if needs to use
 * larger screen than available in {@link CustomPanelAPI} panel.
 */
public abstract class Renderable {

    private Location location = Location.TOP_LEFT;
    private Position offset = new Position(0, 0);
    private boolean withScroller = true;
    private Size size;

    public void render(CustomPanelAPI panel) {
        render(panel, 0, 0);
    }

    public void render(CustomPanelAPI panel, float x, float y) {
        Size size = getSize();
        TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeight(), withScroller);
        render(inner);
        PositionAPI element = panel.addUIElement(inner);
        location.render(element, x + offset.getX(), y + offset.getY());
        log(new Position(x, y));
    }

    public void setScroller(boolean withScroller) {
        this.withScroller = withScroller;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void log() {
        LogHelper.debug(String.format("Rendered %s", this));
    }

    public void log(Position position) {
        LogHelper.debug(String.format("Rendered %s in %s", this, position));
    }

    public Size getSize() {
        if (size == null) {
            LogHelper.warn("Null size found");
        }
        return size;
    }

    public Location getLocation() {
        return location;
    }

    public Position getOffset() {
        return offset;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOffset(Position offset) {
        this.offset = offset;
    }

    public abstract void render(TooltipMakerAPI tooltip);
}
