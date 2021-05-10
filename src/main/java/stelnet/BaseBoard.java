package stelnet;

import stelnet.ui.RenderableIntel;

public abstract class BaseBoard extends RenderableIntel {

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    protected abstract String getTag();
}
