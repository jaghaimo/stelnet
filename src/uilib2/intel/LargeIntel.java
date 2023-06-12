package uilib2.intel;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import uilib2.Layout;

/**
 * Convenient starting point for building small intel classes using `uilib`.
 */
public abstract class LargeIntel extends DrawableIntel {

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        Layout layout = getLayout();
        layout.draw(panel, width, height);
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    protected abstract Layout getLayout();
}
