package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;

import lombok.Getter;

public class ToggleButton extends Button {

    private final String titleOff;
    private final Color colorOff;

    @Getter
    private boolean isOn;

    public ToggleButton(Size size, String titleOn, String titleOff, boolean isEnabled, Color colorOn, Color colorOff,
            boolean isOn) {
        super(size, titleOn, isEnabled, colorOn);
        this.titleOff = titleOff;
        this.colorOff = colorOff;
        this.isOn = isOn;
    }

    @Override
    public Color getColor() {
        return isOn ? super.getColor() : colorOff;
    }

    @Override
    public String getTitle() {
        return isOn ? super.getTitle() : titleOff;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        isOn = !isOn;
        super.onConfirm(ui);
    }
}
