package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class CheckedButton extends DecoratedButton {

    private final boolean isChecked;

    public CheckedButton(Button button, boolean isChecked) {
        super(button);
        this.isChecked = isChecked;
    }

    @Override
    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        ButtonAPI addedButton = button.addButton(tooltip);
        addedButton.setChecked(isChecked);
        return addedButton;
    }
}
