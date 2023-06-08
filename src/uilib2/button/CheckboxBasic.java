package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckboxBasic extends AbstractButton {

    private final float width;
    private final float height;
    private final String text;
    private final Object data;
    private final UICheckboxSize size;
    private final float pad;

    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        return tooltip.addCheckbox(width, height, text, data, size, pad);
    }
}
