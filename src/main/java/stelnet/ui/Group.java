package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class Group extends Renderable {

    private List<Renderable> elements;

    public Group(List<Renderable> elements) {
        this.elements = elements;
    }

    @Override
    public Size getSize() {
        float width = 0;
        float height = 0;
        for (Renderable renderable : elements) {
            Size size = renderable.getSize();
            width = Math.max(width, size.getWidth());
            height = Math.max(height, size.getHeight());
        }
        return new Size(width, height);
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
}
