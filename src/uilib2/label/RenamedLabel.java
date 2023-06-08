package uilib2.label;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class RenamedLabel extends DecoratedLabel {

    private final String title;

    public RenamedLabel(Label label, String title) {
        super(label);
        this.title = title;
    }

    @Override
    public LabelAPI addLabel(TooltipMakerAPI tooltip) {
        LabelAPI addedLabel = label.addLabel(tooltip);
        addedLabel.setText(title);
        return addedLabel;
    }
}
