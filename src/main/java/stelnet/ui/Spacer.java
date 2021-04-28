package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Spacer extends AbstractRenderable {

    public Spacer(float padding) {
        setSize(new Size(padding, padding));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
    }
}
