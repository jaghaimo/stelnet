package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib.property.Position;
import uilib.property.Size;
import uilib2.Drawable;

/**
 * Limited adapter to use uilib2 in uilib. Does not support drawing using CustomPanelAPI.
 */
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
        throw new RuntimeException("UiLib2 does not expose offsets.");
    }

    @Override
    public Size getSize() {
        throw new RuntimeException("UiLib2 does not expose size.");
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        throw new RuntimeException("UiLib2 does not support CustomPanelAPI.");
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        drawable.draw(tooltip);
    }
}
