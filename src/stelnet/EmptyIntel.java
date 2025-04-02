package stelnet;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public class EmptyIntel extends BaseIntelPlugin {

    @Override
    public boolean shouldRemoveIntel() {
        return true;
    }

    @Override
    public boolean isHidden() {
        return true;
    }
}
