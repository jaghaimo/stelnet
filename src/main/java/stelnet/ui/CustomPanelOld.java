package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

@Deprecated
public class CustomPanelOld extends Renderable {

    private CustomPanelAPI renderedPanel;

    public CustomPanelOld(CustomPanelAPI renderedPanel, Size size) {
        this.renderedPanel = renderedPanel;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addCustom(renderedPanel, 0);
        log();
    }

    @Override
    public String toString() {
        return "CustomPanel with " + getSize();
    }
}
