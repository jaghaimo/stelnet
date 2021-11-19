package uilib;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.Getter;
import lombok.Setter;
import stelnet.util.SettingsUtils;
import uilib.property.Size;

@Getter
@Setter
public class ToggleButton extends Button implements TwoStateButton {

    private final String titleOff;
    private boolean isStateOn;
    private Color backgroundSelectedColor = SettingsUtils.getButtonHighlightColor();

    public ToggleButton(
        Size size,
        String toggledOnTitle,
        String toggledOffTitle,
        boolean isEnabled,
        boolean isStateOn
    ) {
        super(size, toggledOnTitle, isEnabled);
        if (size.getWidth() == 0) {
            float maxWidth = Math.max(getTextWidth(toggledOnTitle), getTextWidth(toggledOffTitle));
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
    public void onConfirm(IntelUIAPI ui) {
        toggle();
        super.onConfirm(ui);
    }

    @Override
    public void toggle() {
        setStateOn(!isStateOn);
    }

    @Override
    protected void scaleBackground(float scale) {
        backgroundSelectedColor = Misc.scaleColor(backgroundSelectedColor, scale);
        super.scaleBackground(scale);
    }
}
