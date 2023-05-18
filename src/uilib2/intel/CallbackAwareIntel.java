package uilib2.intel;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class CallbackAwareIntel extends BaseIntelPlugin {

    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        try {
            IntelUiCallback callback = (IntelUiCallback) buttonId;
            callback.onConfirm(ui);
        } catch (Exception e) {}
    }

    public void buttonPressCancelled(Object buttonId, IntelUIAPI ui) {
        try {
            IntelUiCallback callback = (IntelUiCallback) buttonId;
            callback.onCancel(ui);
        } catch (Exception e) {}
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI tooltip) {
        try {
            IntelUiCallback callback = (IntelUiCallback) buttonId;
            callback.getPrompt().show(tooltip);
        } catch (Exception e) {}
    }

    @Override
    public boolean doesButtonHaveConfirmDialog(Object buttonId) {
        try {
            IntelUiCallback callback = (IntelUiCallback) buttonId;
            return callback.hasPrompt();
        } catch (Exception e) {
            return false;
        }
    }
}
