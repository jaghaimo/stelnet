package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import uilib2.Drawable;
import uilib2.Layout;

/**
 * Most basic layout - draw directly on the provided panel.
 */
@RequiredArgsConstructor
public class BasicLayout implements Layout {

    @Delegate
    private final List<Drawable> drawables;

    private final boolean withScroller;

    public BasicLayout() {
        this(new LinkedList<Drawable>(), false);
    }

    public BasicLayout(List<Drawable> drawables) {
        this(drawables, false);
    }

    public BasicLayout(boolean withScroller) {
        this(new LinkedList<Drawable>(), withScroller);
    }

    @Override
    public PositionAPI draw(CustomPanelAPI panel, float width, float height) {
        TooltipMakerAPI tooltip = panel.createUIElement(width, height, withScroller);
        for (Drawable drawable : drawables) {
            drawable.draw(tooltip);
        }
        return panel.addUIElement(tooltip);
    }
}
