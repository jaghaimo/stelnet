package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public interface Renderable {

    public void render(CustomPanelAPI panel);

    public void render(TooltipMakerAPI tooltipMakerAPI);
}
