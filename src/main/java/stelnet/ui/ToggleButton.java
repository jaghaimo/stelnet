package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;

public class ToggleButton extends Button {

    private final String titleOff;
    private final Color colorOff;
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
    public void confirm(IntelUIAPI ui) {
        isOn = !isOn;
        super.confirm(ui);
    }

    public boolean isOn() {
        return isOn;
    }
}
