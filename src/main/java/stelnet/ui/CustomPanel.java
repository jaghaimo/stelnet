package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class CustomPanel extends Renderable {

    private CustomPanelAPI renderedPanel;
    private Size size;

    public CustomPanel(CustomPanelAPI renderedPanel, Size size) {
        this.renderedPanel = renderedPanel;
        this.size = size;
    }

    @Override
    public Size getSize() {
        return size;
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
