package uilib2.intel;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class IntelUiCallback {

    private final List<IntelUiAction> cancelActions = new LinkedList<>();
    private final List<IntelUiAction> confirmActions = new LinkedList<>();

    @Setter
    private IntelUiPrompt prompt;

    public boolean hasPrompt() {
        return prompt != null;
    }

    public void onCancel(IntelUIAPI ui) {
        for (IntelUiAction action : cancelActions) {
            action.act(ui);
        }
    }

    public void onConfirm(IntelUIAPI ui) {
        for (IntelUiAction action : confirmActions) {
            action.act(ui);
        }
    }
}
