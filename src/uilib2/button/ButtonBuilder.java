package uilib2.button;

import lombok.AllArgsConstructor;
import lombok.experimental.ExtensionMethod;
import uilib2.MemoryHelper;

/**
 * Button builder that is using a decorator pattern.
 */
@AllArgsConstructor
@ExtensionMethod({ MemoryHelper.class })
public class ButtonBuilder {

    private Button button;

    public ButtonBuilder setCheckedFromMemoryKey(String memoryKey, boolean defaultValue) {
        boolean isChecked = MemoryHelper.getBoolean(memoryKey, defaultValue);
        button = new CheckedButton(button, isChecked);
        return this;
    }

    public ButtonBuilder setChecked(boolean isChecked) {
        button = new CheckedButton(button, isChecked);
        return this;
    }

    public ButtonBuilder setEnabledFromMemoryKey(String memoryKey, boolean defaultValue) {
        boolean isEnabled = MemoryHelper.getBoolean(memoryKey, defaultValue);
        button = new EnabledButton(button, isEnabled);
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
        button = new ShortcutButton(button, shortcut, putLast);
        return this;
    }

    public Button build() {
        return button;
    }
}
