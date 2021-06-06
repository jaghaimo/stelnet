package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;

import lombok.Getter;
import lombok.Setter;
import stelnet.ui.property.Size;

@Getter
@Setter
public class ToggleButton extends Button implements TwoStateButton {

    private final String titleOff;
    private final Color colorOff;
    private boolean isStateOn;

    public ToggleButton(
            Size size,
            String toggledOnTitle,
            String toggledOffTitle,
            boolean isEnabled,
            Color colorOn,
            Color colorOff,
            boolean isStateOn
    ) {
        super(size, toggledOnTitle, isEnabled, colorOn);
        this.titleOff = toggledOffTitle;
        this.colorOff = colorOff;
        this.isStateOn = isStateOn;
    }

    @Override
    public Color getColor() {
        return isStateOn ? super.getColor() : colorOff;
    }

    @Override
    public String getTitle() {
        return isStateOn ? super.getTitle() : titleOff;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        setStateOn(!isStateOn);
        super.onConfirm(ui);
    }
}
