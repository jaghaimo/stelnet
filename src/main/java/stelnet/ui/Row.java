package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

/**
 * Renders elements in a horizontal line.
 *
 * Calculates size automatically.
 */
public class Row extends Group {

    private int direction = 1;

    public Row(List<Renderable> elements) {
        super(elements);
    }

    public Row(List<Renderable> elements, boolean reversed) {
        super(elements);
        if (reversed) {
            direction = -1;
        }
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            x += size.getWidth() * direction;
        }
    }
}
