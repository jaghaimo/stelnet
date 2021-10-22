package uilib;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.Getter;
import lombok.Setter;
import uilib.property.Size;

@Getter
@Setter
public class AreaCheckbox extends Button implements TwoStateButton {

    private boolean isStateOn;
    private Color offColor;

    public AreaCheckbox(
        Size size,
        String title,
        boolean isEnabled,
        boolean isStateOn,
        Color onColor,
        Color offColor
    ) {
        super(size, title, isEnabled, onColor);
        this.isStateOn = isStateOn;
        this.offColor = offColor;
        setSize(size);
        setWithScroller(false);
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        setStateOn(!isStateOn);
        super.onConfirm(ui);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Size size = getSize();
        Color foregroundColor = getForegroundColor();
        Color backgroundColor = Misc.scaleColor(foregroundColor, 0.7f);
        ButtonAPI button = tooltip.addAreaCheckbox(
            getTitle(),
            this,
            Misc.getGrayColor(),
            backgroundColor,
            foregroundColor,
            size.getWidth() - 4,
            size.getHeight() - 4,
            4
        );
        button.setEnabled(isEnabled());
        button.setChecked(isStateOn);
        if (getShortcut() > 0) {
            button.setShortcut(getShortcut(), false);
        }
    }

    private Color getForegroundColor() {
        if (isStateOn) {
            return getColor();
        }
        return offColor;
    }
}
