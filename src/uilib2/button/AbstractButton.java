package uilib2.button;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

/**
 * Base class for all UI elements that return ButtonAPI: buttons, checkboxes, area checkboxes.
 */
public abstract class AbstractButton implements Button {

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addButton(tooltip);
    }
}
