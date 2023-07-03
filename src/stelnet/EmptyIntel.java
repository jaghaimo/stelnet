package stelnet;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

/**
 * An empty intel that will be removed at next opportunity by IntelManager.
 */
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
