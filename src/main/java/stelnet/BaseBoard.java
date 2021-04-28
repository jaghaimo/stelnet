package stelnet;

import stelnet.ui.AbstractRenderableIntel;

public abstract class BaseBoard extends AbstractRenderableIntel {

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }
}
