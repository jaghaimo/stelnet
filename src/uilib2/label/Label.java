package uilib2.label;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import uilib2.Drawable;

public interface Label extends Drawable {
    public LabelAPI addLabel(TooltipMakerAPI tooltip);
}
