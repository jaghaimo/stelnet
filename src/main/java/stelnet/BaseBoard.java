package stelnet;

import stelnet.ui.RenderableIntel;

public class BaseBoard extends RenderableIntel {

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }
}
