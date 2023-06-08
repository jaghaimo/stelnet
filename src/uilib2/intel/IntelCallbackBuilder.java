package uilib2.intel;

public class IntelCallbackBuilder {

    private final IntelUiCallback callback = new IntelUiCallback();

    public IntelCallbackBuilder addCancelAction(IntelUiAction action) {
        callback.getCancelActions().add(action);
        return this;
    }

    public IntelCallbackBuilder addConfirmAction(IntelUiAction action) {
        callback.getConfirmActions().add(action);
        return this;
    }

    public IntelCallbackBuilder addPromptDisplay(IntelUiPrompt prompt) {
        callback.setPrompt(prompt);
        return this;
    }

    public IntelUiCallback build() {
        return callback;
    }
}
