package uilib2.button;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;

/**
 * Base class for all UI elements that return ButtonAPI: buttons, checkboxes, area checkboxes.
 */
public abstract class AbstractButton implements Button {

    @Override
    public UIComponentAPI draw(TooltipMakerAPI tooltip) {
        return addButton(tooltip);
    }
}
