package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Padding extends Renderable {

    private float padding;

    public Padding(float padding) {
        this.padding = padding;
    }

    @Override
    public Size getSize() {
        return new Size(padding, padding);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
    }
}
