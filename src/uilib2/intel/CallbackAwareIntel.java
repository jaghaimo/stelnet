package uilib2.intel;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.extern.log4j.Log4j;

@Log4j
public abstract class CallbackAwareIntel extends BaseIntelPlugin {

    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        try {
            IntelUiCallback callback = (IntelUiCallback) buttonId;
            callback.onConfirm(ui);
        } catch (Exception e) {
            log.debug("Skipping unsupported 'buttonPressConfirmed' buttonId type'");
        }
    }

    public void buttonPressCancelled(Object buttonId, IntelUIAPI ui) {
        try {
            IntelUiCallback callback = (IntelUiCallback) buttonId;
            callback.onCancel(ui);
        } catch (Exception e) {
            log.debug("Skipping unsupported 'buttonPressCancelled' buttonId type'");
        }
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI tooltip) {
        try {
            IntelUiCallback callback = (IntelUiCallback) buttonId;
            callback.getPrompt().show(tooltip);
        } catch (Exception e) {
            log.debug("Skipping unsupported 'createConfirmationPrompt' buttonId type'");
        }
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
