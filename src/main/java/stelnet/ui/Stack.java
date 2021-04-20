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

    private int direction;

    public Stack(boolean reversed, Renderable... elements) {
        super(Arrays.asList(elements));
        if (reversed) {
            direction = -1;
        }
    }

    public Stack(Renderable... elements) {
        super(Arrays.asList(elements));
        direction = 1;
    }

    public Stack(List<Renderable> elements) {
        super(elements);
        direction = 1;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            y += size.getHeight() * direction;
        }
    }
}
