package stelnet.board.exploration;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import uilib2.Drawable;
import uilib2.UiConstants;

@RequiredArgsConstructor
public class ShiftedButton implements Drawable {

    private final Drawable drawable;
    private final float width;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        drawable.draw(tooltip);
        float shiftX = (width / 2) + UiConstants.BUTTON_PADDING;
        tooltip.getPrev().getPosition().setXAlignOffset(shiftX);
        tooltip.getPrev().getPosition().setYAlignOffset(UiConstants.BUTTON_HEIGHT);
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setXAlignOffset(-shiftX - 1); // why does it need -1?
    }
}
