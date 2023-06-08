package uilib2;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Spacer implements Drawable {

    private final float height;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(height);
    }
}
