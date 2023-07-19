package uilib2.intel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.Collections;
import java.util.List;
import uilib2.Drawable;

/**
 * Convenient starting point for building small intel classes using `uilib2`.
 */
public abstract class SmallIntel extends DrawableIntel {

    @Override
    public void createSmallDescription(final TooltipMakerAPI tooltip, final float width, final float height) {
        for (final Drawable drawable : getDrawableList(width, height)) {
            drawable.draw(tooltip);
        }
    }

    @Override
    public boolean hasSmallDescription() {
        return true;
    }

    protected List<Drawable> getDrawableList(final float width, final float height) {
        return Collections.emptyList();
    }
}
