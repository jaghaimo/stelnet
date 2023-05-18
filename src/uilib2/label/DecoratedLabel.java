package uilib2.label;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DecoratedLabel implements Label {

    protected final Label label;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addLabel(tooltip);
    }
}
