package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import uilib.property.Size;

public class C2Button extends Button {

    public C2Button(Size size, String title, boolean isEnabled) {
        super(size, title, isEnabled);
        overrideSize(0);
    }

    public C2Button(Size size, String title, boolean isEnabled, Color color) {
        super(size, title, isEnabled, color);
        overrideSize(0);
    }

    public C2Button(Size size, String title, boolean isEnabled, Color textColor, Color backgroundColor) {
        super(size, title, isEnabled, textColor, backgroundColor);
        overrideSize(0);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        super.render(panel, x, y);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        setCutStyle(CutStyle.C2_MENU);
        tooltip.setButtonFontVictor14();
        super.render(tooltip);
        tooltip.setButtonFontDefault();
    }

    protected void overrideSize(float width) {
        setSize(new Size(width + getSize().getWidth(), 30));
    }
}
