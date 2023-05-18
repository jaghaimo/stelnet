package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import uilib2.Drawable;

/**
 * Base class for all UI elements that return ButtonAPI: buttons, checkboxes, area checkboxes.
 */
public interface Button extends Drawable {
    public ButtonAPI addButton(TooltipMakerAPI tooltip);
}
