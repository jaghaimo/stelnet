package stelnet;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.ui.Callable;

public abstract class BaseBoard extends BaseIntelPlugin {

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        Callable callable = (Callable) buttonId;
        callable.callback();
        ui.updateUIForItem(this);
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
