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
        return isStateOn ? SettingsUtils.getButtonHighlightColor() : Misc.getDarkPlayerColor();
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
}
