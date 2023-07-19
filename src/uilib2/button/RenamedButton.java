package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class RenamedButton extends DecoratedButton {

    private final String newName;

    public RenamedButton(Button button, String newName) {
        super(button);
        this.newName = newName;
    }

    @Override
    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        ButtonAPI addedButton = super.addButton(tooltip);
        addedButton.setText(newName);
        return addedButton;
    }
}
