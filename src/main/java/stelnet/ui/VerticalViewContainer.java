package stelnet.ui;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.extern.log4j.Log4j;

/**
 * Renders elements in a vertical line.
 *
 * Calculates size automatically if needed. Only usable in large intel or
 * {@link CustomPanel}.
 */
@Log4j
public class VerticalViewContainer extends Group {

    public VerticalViewContainer(AbstractRenderable... elements) {
        super(Arrays.asList(elements));
    }

    public VerticalViewContainer(List<AbstractRenderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (AbstractRenderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            y += size.getHeight() * getLocation().getVerticalDirection();
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        log.error("Cannot render Stack in small intel. Use Group or prerender in CustomPanel instead.");
    }

    @Override
    public String toString() {
        return String.format("Stack(%d) with %s", getElements().size(), getSize());
    }

    @Override
    protected void setCalculatedSize() {
        super.setCalculatedSize();
        Size size = super.getSize();
        float height = 0;
        for (AbstractRenderable renderable : getElements()) {
            height += renderable.getSize().getHeight();
        }
        setSize(new Size(size.getWidth(), height));
    }
}
