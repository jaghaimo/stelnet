package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ButtonColored extends AbstractButton {

    private final String text;
    private final Object data;
    private final Color base;
    private final Color bg;
    private final float width;
    private final float height;
    private final float pad;

    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        return tooltip.addButton(text, data, base, bg, width, height, pad);
    }
}
