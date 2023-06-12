package uilib2.label;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;

public abstract class AbstractLabel implements Label {

    @Override
    public UIComponentAPI draw(TooltipMakerAPI tooltip) {
        addLabel(tooltip);
        return tooltip.getPrev();
    }
}
