package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Spacer extends AbstractRenderable {

    public Spacer(float padding) {
        setWithScroller(false);
        setSize(new Size(padding, padding));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(getSize().getHeight());
    }
}
