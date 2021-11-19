package uilib;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.Getter;
import lombok.Setter;
import uilib.property.Size;

@Getter
@Setter
public class AreaCheckbox extends Button implements TwoStateButton {

    private boolean isStateOn;
    private int padding = 4;

    public AreaCheckbox(Size size, String title, boolean isEnabled, boolean isStateOn) {
        super(size, title, isEnabled);
        this.isStateOn = isStateOn;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        toggle();
        super.onConfirm(ui);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Size size = getSize();
        Color foregroundColor = getTextColor();
        Color backgroundColor = getBackgroundColor();
        ButtonAPI button = tooltip.addAreaCheckbox(
            getTitle(),
            this,
            foregroundColor,
            backgroundColor,
            foregroundColor,
            size.getWidth() - padding,
            size.getHeight() - padding,
            padding / 2
        );
        button.setEnabled(isEnabled());
        button.setChecked(isStateOn);
        if (getShortcut() > 0) {
            button.setShortcut(getShortcut(), false);
        }
    }

    @Override
    public void toggle() {
        setStateOn(!isStateOn);
    }
}
