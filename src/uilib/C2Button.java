package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import uilib.property.Size;

public class C2Button extends Button {

    public C2Button(final Size size, final String title, final boolean isEnabled) {
        super(size, title, isEnabled);
        overrideSize(0);
    }

    public C2Button(final Size size, final String title, final boolean isEnabled, final Color color) {
        super(size, title, isEnabled, color);
        overrideSize(0);
    }

    @Override
    public void render(final CustomPanelAPI panel, final float x, final float y) {
        super.render(panel, x, y);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        setCutStyle(CutStyle.C2_MENU);
        tooltip.setButtonFontVictor14();
        super.render(tooltip);
        tooltip.setButtonFontDefault();
    }

    protected void overrideSize(final float width) {
        setSize(new Size(width + getSize().getWidth(), UiConstants.VICTOR_14_BUTTON_HEIGHT));
    }
}
