package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AreaCheckboxBasic extends AbstractButton {

    private final String text;
    private final Object data;
    private final Color base;
    private final Color bg;
    private final Color bright;
    private final float width;
    private final float height;
    private final float pad;

    public ButtonAPI addButton(final TooltipMakerAPI tooltip) {
        return tooltip.addAreaCheckbox(text, data, base, bg, bright, width, height, pad);
    }
}
