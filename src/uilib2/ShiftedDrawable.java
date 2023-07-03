package uilib2;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;

/**
 * Generic shifted `Drawable`.
 */
@RequiredArgsConstructor
public class ShiftedDrawable implements Drawable {

    private final Drawable drawable;
    private final float shiftX;
    private final float shiftY;

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final float heightSoFar = tooltip.getHeightSoFar();
        final UIComponentAPI component = drawable.draw(tooltip);
        tooltip.getPrev().getPosition().setXAlignOffset(shiftX);
        tooltip.getPrev().getPosition().setYAlignOffset(shiftY);
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setXAlignOffset(-shiftX - 1); // why does it need -1?
        tooltip.setHeightSoFar(heightSoFar);
        return component;
    }
}
