package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;

import lombok.Getter;

@Getter
public class ToggleButton extends Button {

    private final String titleOff;
    private final Color colorOff;

    private boolean isToggledOn;

    public ToggleButton(
            Size size,
            String toggledOnTitle,
            String toggledOffTitle,
            boolean isEnabled,
            Color colorOn,
            Color colorOff,
            boolean isToggledOn
    ) {
        super(size, toggledOnTitle, isEnabled, colorOn);
        this.titleOff = toggledOffTitle;
        this.colorOff = colorOff;
        this.isToggledOn = isToggledOn;
    }

    @Override
    public Color getColor() {
        return isToggledOn ? super.getColor() : colorOff;
    }

    @Override
    public String getTitle() {
        return isToggledOn ? super.getTitle() : titleOff;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        isToggledOn = !isToggledOn;
        super.onConfirm(ui);
    }
}
