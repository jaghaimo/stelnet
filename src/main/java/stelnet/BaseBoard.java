package stelnet;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.ui.Callable;
import stelnet.ui.GridData;
import stelnet.ui.GridRenderer;
import stelnet.ui.Size;

public abstract class BaseBoard extends BaseIntelPlugin {

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        Callable callable = (Callable) buttonId;
        callable.callback();
        ui.updateUIForItem(this);
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        Size size = new Size(width, height);
        GridRenderer renderer = new GridRenderer(size, getGridData(size));
        renderer.render(panel);
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

    protected abstract GridData getGridData(Size size);
}
