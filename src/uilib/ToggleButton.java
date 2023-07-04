package uilib;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import uilib.property.Size;

@Getter
@Setter
public class ToggleButton extends Button implements TwoStateButton {

    private final String titleOff;
    private boolean isStateOn;
    private Color backgroundSelectedColor = ColorHelper.buttonHighlightColor();

    public ToggleButton(
        Size size,
        final String toggledOnTitle,
        final String toggledOffTitle,
        final boolean isEnabled,
        final boolean isStateOn
    ) {
        super(size, toggledOnTitle, isEnabled);
        if (size.getWidth() == 0) {
            final float maxWidth = Math.max(
                10 + Global.getSettings().computeStringWidth(toggledOnTitle, Fonts.DEFAULT_SMALL),
                10 + Global.getSettings().computeStringWidth(toggledOffTitle, Fonts.DEFAULT_SMALL)
            );
            size = new Size(maxWidth, size.getHeight());
        }
        this.titleOff = toggledOffTitle;
        this.isStateOn = isStateOn;
    }

    @Override
    public Color getBackgroundColor() {
        return isStateOn ? backgroundSelectedColor : super.getBackgroundColor();
    }

    @Override
    public String getTitle() {
        return isStateOn ? super.getTitle() : titleOff;
    }

    @Override
    public void onConfirm(final IntelUIAPI ui) {
        toggle();
        super.onConfirm(ui);
    }

    @Override
    public void scaleBackground(final float scale) {
        backgroundSelectedColor = Misc.scaleColor(backgroundSelectedColor, scale);
        super.scaleBackground(scale);
    }

    @Override
    public void toggle() {
        setStateOn(!isStateOn);
    }
}
