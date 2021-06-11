package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.extern.log4j.Log4j;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

/**
 * Renders elements in a horizontal line.
 *
 * Calculates size automatically if needed. Only usable in large intel or
 * {@link CustomPanel}.
 */
@Log4j
public class HorizontalViewContainer extends Group {

    public HorizontalViewContainer(AbstractRenderable... elements) {
        super(elements);
    }

    public HorizontalViewContainer(List<AbstractRenderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        log(new Position(x, y));
        for (AbstractRenderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            x += size.getWidth() * getLocation().getHorizontalDirection();
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        log.error("Cannot render Stack in small intel. Use Group or prerender in CustomPanel instead.");
    }

    @Override
    public String toString() {
        return String.format("Row(%d) with %s", getElements().size(), getSize());
    }

    @Override
    protected void setCalculatedSize() {
        super.setCalculatedSize();
        Size size = super.getSize();
        float width = 0;
        for (AbstractRenderable renderable : getElements()) {
            width += renderable.getSize().getWidth();
        }
        setSize(new Size(width, size.getHeight()));
    }
}
