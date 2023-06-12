package uilib2;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Spacer implements Drawable {

    private final float height;

    @Override
    public UIComponentAPI draw(TooltipMakerAPI tooltip) {
        return tooltip.addSpacer(height);
    }
}
