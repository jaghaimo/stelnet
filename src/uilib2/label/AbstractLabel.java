package uilib2.label;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class AbstractLabel implements Label {

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addLabel(tooltip);
    }
}
