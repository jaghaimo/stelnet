package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import lombok.extern.log4j.Log4j;
import uilib.property.Position;
import uilib.property.Size;

/**
 * Renders elements in a horizontal line.
 *
 * Calculates size automatically if needed. Only usable in large intel or
 * {@link CustomPanel}.
 */
@Log4j
public class HorizontalViewContainer extends Group {

    public HorizontalViewContainer(Renderable... elements) {
        super(elements);
    }

    public HorizontalViewContainer(List<Renderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        log(new Position(x, y));
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            Position offset = renderable.getOffset();
            renderable.render(panel, x, y);
            x += (offset.getX() + size.getWidth()) * getLocation().getHorizontalDirection();
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        log.error(
            "Cannot render HorizontalViewContainer in small intel. Use Group or prerender in CustomPanel instead."
        );
    }

    @Override
    public String toString() {
        return String.format("HorizontalViewContainer(%d) with %s", getElements().size(), getSize());
    }

    @Override
    protected void setCalculatedSize() {
        super.setCalculatedSize();
        Size size = super.getSize();
        float width = 0;
        for (Renderable renderable : getElements()) {
            width += renderable.getSize().getWidth();
        }
        setSize(new Size(width, size.getHeight()));
    }
}
