package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;

public class CustomPanel extends AbstractRenderable {

    private CustomPanelAPI customPanel;
    private final AbstractRenderable renderable;

    public CustomPanel(AbstractRenderable renderable) {
        this.renderable = renderable;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        Size size = getSize();
        customPanel = panel.createCustomPanel(size.getWidth(), size.getHeight(), null);
        renderable.setOffset(new Position(x, y));
        renderable.render(customPanel);
        PositionAPI element = panel.addComponent(customPanel);
        Position offset = getOffset();
        getLocation().render(element, x + offset.getX(), y + offset.getY());
        log(new Position(x, y));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (customPanel == null) {
            LogHelper.error("Cannot render CustomPanel in small intel");
            return;
        }
        tooltip.addCustom(customPanel, 0);
    }
}
