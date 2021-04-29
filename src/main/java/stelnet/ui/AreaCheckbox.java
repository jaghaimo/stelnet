package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaCheckbox extends Button {

    private boolean isChecked;
    private Color offColor;

    public AreaCheckbox(Size size, String title, boolean isEnabled, boolean isChecked, Color onColor, Color offColor) {
        super(size, title, isEnabled, onColor);
        this.isChecked = isChecked;
        this.offColor = offColor;
        setSize(size);
        setWithScroller(false);
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        setChecked(!isChecked);
        super.onConfirm(ui);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Size size = getSize();
        Color foregroundColor = getForegroundColor();
        Color backgroundColor = Misc.scaleColor(foregroundColor, 0.7f);
        ButtonAPI button = tooltip.addAreaCheckbox(getTitle(), this, Misc.getGrayColor(), backgroundColor,
                foregroundColor, size.getWidth() - 4, size.getHeight() - 4, 4);
        button.setEnabled(isEnabled());
        button.setChecked(isChecked);
        if (getShortcut() > 0) {
            button.setShortcut(getShortcut(), false);
        }
    }

    private Color getForegroundColor() {
        if (isChecked) {
            return getColor();
        }
        return offColor;
    }
}
