package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ColoredButton implements Button {

    private final String text;
    private final Object data;
    private final Color base;
    private final Color bg;
    private final float width;
    private final float height;
    private final float pad;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addButton(tooltip);
    }

    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        return tooltip.addButton(text, data, base, bg, width, height, pad);
    }
}
