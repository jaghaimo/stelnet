package uilib2.button;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ButtonBuilder {

    private Button button;

    public void setEnabled(boolean isEnabled) {
        button = new EnabledButton(button, isEnabled);
    }

    public void setShortcut(int shortcut, boolean putLast) {
        button = new ShortcutButton(button, shortcut, putLast);
    }

    public Button build() {
        return button;
    }
}
