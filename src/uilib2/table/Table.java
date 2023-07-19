package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIPanelAPI;
import uilib2.Drawable;

public interface Table extends Drawable {
    public UIPanelAPI addTable(TooltipMakerAPI tooltip);
}
