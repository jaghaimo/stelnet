package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class EnabledButton extends DecoratedButton {

    private final boolean isEnabled;

    public EnabledButton(Button button, boolean isEnabled) {
        super(button);
        this.isEnabled = isEnabled;
    }

    @Override
    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        ButtonAPI addedButton = button.addButton(tooltip);
        addedButton.setEnabled(isEnabled);
        return addedButton;
    }
}
