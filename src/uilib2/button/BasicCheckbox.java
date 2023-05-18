package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicCheckbox implements Button {

    private final float width;
    private final float height;
    private final String text;
    private final UICheckboxSize size;
    private final float pad;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addButton(tooltip);
    }

    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        return tooltip.addCheckbox(width, height, text, size, pad);
    }
}