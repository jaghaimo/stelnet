package stelnet.filter;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public abstract class IntelFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof BaseIntelPlugin) {
            return acceptIntel((BaseIntelPlugin) object);
        }
        return super.accept(object);
    }

    protected abstract boolean acceptIntel(BaseIntelPlugin intel);
}
