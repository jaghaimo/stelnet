package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Spacer extends Renderable {

    public Spacer(float padding) {
        setScroller(false);
        setSize(new Size(padding, padding));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
    }
}
