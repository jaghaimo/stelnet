package uilib2.intel;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import lombok.extern.log4j.Log4j;
import uilib2.Layout;

/**
 * Convenient starting point for building large intel classes using `uilib2`.
 */
@Log4j
public abstract class LargeIntel extends DrawableIntel {

    @Override
    public void createLargeDescription(final CustomPanelAPI panel, final float width, final float height) {
        final Layout layout = getLayout(width, height);
        if (layout == null) {
            log.warn("Got null layout for intel " + this);
        } else {
            layout.draw(panel, width, height).inTL(0, 0);
        }
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    protected abstract Layout getLayout(float width, float height);
}
