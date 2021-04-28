package stelnet.ui;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;

/**
 * Renders elements in a horizontal line.
 *
 * Calculates size automatically if needed. Only usable in large intel or
 * {@link CustomPanel}.
 */
public class Row extends Group {

    public Row(AbstractRenderable... elements) {
        super(Arrays.asList(elements));
    }

    public Row(List<AbstractRenderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (AbstractRenderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            x += size.getWidth() * getLocation().getHorizontalDirection();
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        LogHelper.error("Cannot render Stack in small intel. Use Group or prerender in CustomPanel instead.");
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
