package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Spacer extends Renderable {

    private float height;

    public Spacer(float height) {
        this.height = height;
    }

    @Override
    public Size getSize() {
        return new Size(0, height);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(height);

    }
}
