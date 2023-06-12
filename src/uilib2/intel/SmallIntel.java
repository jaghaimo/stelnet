package uilib2.intel;

import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

import uilib2.Drawable;

/**
 * Convenient starting point for building small intel classes using `uilib2`.
 */
public abstract class SmallIntel extends DrawableIntel {

    @Override
    public void createSmallDescription(TooltipMakerAPI tooltip, float width, float height) {
        for (Drawable drawable : getDrawableList(width, height)) {
            drawable.draw(tooltip);
        }
    }

    @Override
    public boolean hasSmallDescription() {
        return true;
    }

    protected List<Drawable> getDrawableList(float width, float height) {
        return Collections.<Drawable>emptyList();
    }
}
