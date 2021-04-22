package stelnet.ui;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

/**
 * Renders elements in a horizontal line.
 *
 * Calculates size automatically.
 */
public class Row extends Group {

    public Row(Renderable... elements) {
        super(Arrays.asList(elements));
    }

    public Row(List<Renderable> elements) {
        super(elements);
    }

    @Override
    public Size getSize() {
        Size size = super.getSize();
        float width = 0;
        for (Renderable renderable : getElements()) {
            width += renderable.getSize().getWidth();
        }
        return new Size(width, size.getHeight());
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            x += size.getWidth();
        }
    }

    @Override
    public String toString() {
        return String.format("Row(%d) with %s", getElements().size(), getSize());
    }
}
