package uilib2.button;

import lombok.AllArgsConstructor;

/**
 * Button builder that is using a decorator pattern.
 */
@AllArgsConstructor
public class ButtonBuilder {

    private Button button;

    public ButtonBuilder setChecked(boolean isChecked) {
        button = new CheckedButton(button, isChecked);
        return this;
    }

    public ButtonBuilder setEnabled(boolean isEnabled) {
        button = new EnabledButton(button, isEnabled);
        return this;
    }

    public ButtonBuilder setName(String name) {
        button = new RenamedButton(button, name);
        return this;
    }

    public ButtonBuilder setShortcut(int shortcut, boolean putLast) {
        if (shortcut > 0) {
            button = new ShortcutButton(button, shortcut, putLast);
        }
        return this;
    }

    public Button build() {
        return button;
    }
}
