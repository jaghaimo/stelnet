package uilib2;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Rectangle implements Drawable {

    private final Color color;
    private final float width;
    private final float height;
    private final float thickness;

    public Rectangle(final float width, final float height) {
        this(Misc.getHighlightColor(), width, height, 1);
    }

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final UIComponentAPI component = tooltip.createRect(color, thickness);
        component.getPosition().setSize(width, height);
        tooltip.addCustom(component, 0);
        return component;
    }
}
