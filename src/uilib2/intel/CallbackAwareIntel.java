package uilib2.intel;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.extern.log4j.Log4j;

@Log4j
public abstract class CallbackAwareIntel extends BaseIntelPlugin {

    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        IntelUiCallback callback = getCallback(buttonId);
        if (isSupported(callback)) {
            log.debug("Processing 'buttonPressConfirmed'");
            callback.onConfirm(ui);
        }
    }

    public void buttonPressCancelled(Object buttonId, IntelUIAPI ui) {
        IntelUiCallback callback = getCallback(buttonId);
        if (isSupported(callback)) {
            log.debug("Processing 'buttonPressCancelled'");
            callback.onCancel(ui);
        }
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI tooltip) {
        IntelUiCallback callback = getCallback(buttonId);
        if (isSupported(callback)) {
            log.debug("Processing 'createConfirmationPrompt'");
            callback.getPrompt().show(tooltip);
        }
    }

    @Override
    public boolean doesButtonHaveConfirmDialog(Object buttonId) {
        IntelUiCallback callback = getCallback(buttonId);
        if (isSupported(callback)) {
            log.debug("Processing 'doesButtonHaveConfirmDialog'");
            return callback.hasPrompt();
        }
        return false;
    }

    private IntelUiCallback getCallback(Object buttonId) {
        if (buttonId instanceof IntelUiCallback) {
            return (IntelUiCallback) buttonId;
        }
        return null;
    }

    private boolean isSupported(IntelUiCallback callback) {
        return callback != null;
    }
}
