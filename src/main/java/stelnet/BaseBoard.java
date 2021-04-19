package stelnet;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;
import stelnet.ui.Callable;

public abstract class BaseBoard extends BaseIntelPlugin {

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        LogHelper.debug("Calling callback.confirm()");
        Callable callable = (Callable) buttonId;
        callable.confirm(ui);
        ui.updateUIForItem(this);
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI prompt) {
        LogHelper.debug("Calling callback.prompt()");
        Callable callable = (Callable) buttonId;
        callable.prompt(prompt);
    }

    @Override
    public boolean doesButtonHaveConfirmDialog(Object buttonId) {
        Callable callable = (Callable) buttonId;
        return callable.hasPrompt();
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
}
