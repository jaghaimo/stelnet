package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

@RequiredArgsConstructor
@Log4j
public class CustomPanel extends AbstractRenderable {

    private final AbstractRenderable renderable;
    private CustomPanelAPI customPanel;

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        log(new Position(x, y));
        Size size = getSize();
        customPanel = panel.createCustomPanel(size.getWidth(), size.getHeight(), null);
        renderable.render(customPanel, x, y);
        PositionAPI element = panel.addComponent(customPanel);
        Position offset = getOffset();
        getLocation().render(element, x + offset.getX(), y + offset.getY());
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (customPanel == null) {
            log.error(
                    "Cannot render CustomPanel in small intel. Create CustomPanelAPI and call render(CustomPanelAPI panel) to prerender it first.");
            return;
        }
        tooltip.addCustom(customPanel, 0);
    }
}
