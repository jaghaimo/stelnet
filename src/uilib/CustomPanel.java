package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import uilib.property.Position;
import uilib.property.Size;

@RequiredArgsConstructor
@Log4j
public class CustomPanel extends RenderableComponent {

    private final Renderable renderable;
    private CustomPanelAPI customPanel;

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        log(new Position(x, y));
        Size size = getSize();
        customPanel = panel.createCustomPanel(size.getWidth(), size.getHeight(), null);
        renderable.render(customPanel, x, y);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (customPanel == null) {
            log.error(
                "Cannot render CustomPanel in small intel. Create CustomPanelAPI and call `render(CustomPanelAPI panel, float x, float y)` to pre-render it first."
            );
            return;
        }
        tooltip.addCustom(customPanel, 0);
    }
}
