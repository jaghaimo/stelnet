package uilib2.label;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DecoratedLabel extends AbstractLabel {

    private final Label label;

    public LabelAPI addLabel(TooltipMakerAPI tooltip) {
        return label.addLabel(tooltip);
    }
}
