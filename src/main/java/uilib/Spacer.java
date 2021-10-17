package uilib;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

import uilib.property.Size;

public class Spacer extends AbstractRenderable {

    public Spacer(float padding) {
        setSize(new Size(padding, padding));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        float height = getSize().getHeight();
        tooltip.addSpacer(height);
    }
}
