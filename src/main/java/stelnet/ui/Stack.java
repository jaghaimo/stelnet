package stelnet.ui;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

/**
 * Renders elements in a vertical line.
 *
 * Calculates size automatically.
 */
public class Stack extends Group {

    private int direction = 1;

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
    public Size getSize() {
        Size size = super.getSize();
        float height = 0;
        for (Renderable renderable : getElements()) {
            height += renderable.getSize().getHeight();
        }
        return new Size(size.getWidth(), height);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            y += size.getHeight() * direction;
        }
    }

    @Override
    public String toString() {
        return String.format("Stack(%d,%d) with %s", getElements().size(), direction, getSize());
    }
}
