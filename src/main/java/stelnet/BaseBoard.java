package stelnet;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;
import stelnet.ui.ButtonHandler;

public abstract class BaseBoard extends BaseIntelPlugin {

    @Override
    public void buttonPressCancelled(Object buttonId, IntelUIAPI ui) {
        LogHelper.debug("Calling cancel()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onCancel(ui);
        redraw(ui);
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        LogHelper.debug("Calling confirm()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onConfirm(ui);
        redraw(ui);
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI prompt) {
        LogHelper.debug("Calling prompt()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onPrompt(prompt);
    }

    @Override
    public boolean doesButtonHaveConfirmDialog(Object buttonId) {
        LogHelper.debug("Calling hasPrompt()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        return handler.hasPrompt();
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }

    private void redraw(IntelUIAPI ui) {
        ui.recreateIntelUI();
        ui.updateUIForItem(this);
    }
}
