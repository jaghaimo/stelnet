package stelnet.ui;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;

/**
 * Renders elements in a vertical line.
 *
 * Calculates size automatically if needed.
 */
public class Stack extends Group {

    @Deprecated
    private int direction = 1;

    @Deprecated
    public Stack(boolean reversed, Renderable... elements) {
        super(Arrays.asList(elements));
        if (reversed) {
            direction = -1;
        }
    }

    public Stack(Renderable... elements) {
        super(Arrays.asList(elements));
    }

    public Stack(List<Renderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            y += size.getHeight() * getLocation().getVerticalDirection();
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        LogHelper.error("Cannot render Stack in small intel. Use Group instead.");
    }

    @Override
    public String toString() {
        return String.format("Stack(%d,%d) with %s", getElements().size(), direction, getSize());
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
