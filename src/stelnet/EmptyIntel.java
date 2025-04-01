package stelnet;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public class EmptyIntel extends BaseIntelPlugin {

    protected String getName() {
        return "Empty Intel";
    }

    @Override
    public boolean shouldRemoveIntel() {
        return true;
    }

    @Override
    public boolean isHidden() {
        return true;
    }
}
