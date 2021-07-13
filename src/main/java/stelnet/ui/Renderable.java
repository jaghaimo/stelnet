package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.ui.property.Size;

public interface Renderable {

    public Size getSize();

    public void render(CustomPanelAPI panel, float x, float y);

    public void render(TooltipMakerAPI tooltip);
}
