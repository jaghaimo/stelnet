package uilib2.button;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class AbstractButton implements Button {

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addButton(tooltip);
    }
}
