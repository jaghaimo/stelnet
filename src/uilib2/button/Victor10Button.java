package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Victor10Button extends DecoratedButton {

    public Victor10Button(Button button) {
        super(button);
    }

    @Override
    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        tooltip.setButtonFontVictor10();
        ButtonAPI buttonApi = super.addButton(tooltip);
        tooltip.setButtonFontDefault();
        return buttonApi;
    }
}
