package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class ShortcutButton extends DecoratedButton {

    private final int shortcut;
    private final boolean putLast;

    public ShortcutButton(Button button, int shortcut, boolean putLast) {
        super(button);
        this.shortcut = shortcut;
        this.putLast = putLast;
    }

    @Override
    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        ButtonAPI addedButton = super.addButton(tooltip);
        addedButton.setShortcut(shortcut, putLast);
        return addedButton;
    }
}
