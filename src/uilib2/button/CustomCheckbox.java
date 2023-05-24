package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomCheckbox extends AbstractButton {

    private final float width;
    private final float height;
    private final String text;
    private final Object data;
    private final String font;
    private final Color textColor;
    private final UICheckboxSize size;
    private final float pad;

    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        return tooltip.addCheckbox(width, height, text, data, font, textColor, size, pad);
    }
}
