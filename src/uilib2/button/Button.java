package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import uilib2.Drawable;

public abstract interface Button extends Drawable {
    public abstract ButtonAPI addButton(TooltipMakerAPI tooltip);
}
