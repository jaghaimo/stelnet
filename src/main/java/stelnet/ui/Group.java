package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Group extends Renderable {

    private List<Renderable> elements;
    private Size size;

    public Group(List<Renderable> elements) {
        this.elements = elements;
    }

    public Group(List<Renderable> elements, Size size) {
        this.elements = elements;
        this.size = size;
    }

    @Override
    public Size getSize() {
        if (size == null) {
            size = calculateSize();
        }
        return size;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            renderable.render(panel, x, y);
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        for (Renderable renderable : getElements()) {
            renderable.render(tooltip);
        }
    }

    protected List<Renderable> getElements() {
        return elements;
    }

    private Size calculateSize() {
        float width = 0;
        float height = 0;
        for (Renderable renderable : elements) {
            Size size = renderable.getSize();
            width = Math.max(width, size.getWidth());
            height = Math.max(height, size.getHeight());
        }
        return new Size(width, height);
    }
}
