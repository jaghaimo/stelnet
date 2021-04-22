package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Spacer extends Renderable {

    private float width;
    private float height;

    public Spacer(float padding) {
        this.width = padding;
        this.height = padding;
        this.withScroller = false;
    }

    @Override
    public Size getSize() {
        return new Size(width, height);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
    }
}
