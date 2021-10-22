package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import lombok.extern.log4j.Log4j;
import uilib.property.Position;
import uilib.property.Size;

/**
 * Renders elements in a vertical line.
 *
 * Calculates size automatically if needed. Only usable in large intel or
 * {@link CustomPanel}.
 */
@Log4j
public class VerticalViewContainer extends Group {

    public VerticalViewContainer(Renderable... elements) {
        super(elements);
    }

    public VerticalViewContainer(List<Renderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        log(new Position(x, y));
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            y += size.getHeight() * getLocation().getVerticalDirection();
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        log.error(
            "Cannot render VerticalViewContainer in small intel. Use Group or pre-render in CustomPanel instead."
        );
    }

    @Override
    public String toString() {
        return String.format(
            "VerticalViewContainer(%d) with %s",
            getElements().size(),
            getSize()
        );
    }

    @Override
    protected void setCalculatedSize() {
        super.setCalculatedSize();
        Size size = super.getSize();
        float height = 0;
        for (Renderable renderable : getElements()) {
            height += renderable.getSize().getHeight();
        }
        setSize(new Size(size.getWidth(), height));
    }
}
