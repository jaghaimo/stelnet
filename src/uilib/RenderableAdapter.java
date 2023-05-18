package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import uilib.property.Position;
import uilib.property.Size;
import uilib2.Drawable;

/**
 * Limited adapter to use uilib2 in uilib. Does not support drawing using CustomPanelAPI.
 */
@Log4j
@RequiredArgsConstructor
public class RenderableAdapter implements Renderable {

    private final Drawable drawable;

    public static List<Renderable> convertToRenderables(List<Drawable> drawables) {
        List<Renderable> renderables = new LinkedList<>();
        for (Drawable drawable : drawables) {
            renderables.add(new RenderableAdapter(drawable));
        }
        return renderables;
    }

    @Override
    public Position getOffset() {
        log.warn("UiLib does not expose offset.");
        return new Position(0, 0);
    }

    @Override
    public Size getSize() {
        log.warn("UiLib does not expose size.");
        return new Size(0, 0);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        log.warn("UiLib2 does not support CustomPanelAPI.");
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        drawable.draw(tooltip);
    }
}
