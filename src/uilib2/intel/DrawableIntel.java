package uilib2.intel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;

/**
 * DrawableIntel stub that only provides `createIntelInfo` implementation.
 *
 * Your intel class will NEED to override either `hasLargeDescription` or `hasSmallDescription`.
 */
public abstract class DrawableIntel extends CallbackAwareIntel {

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        DrawableIntelInfo intelInfo = getIntelInfo();
        Color bulletColor = getBulletColorForMode(mode);
        Color titleColor = getTitleColor(mode);
        intelInfo.draw(info, bulletColor, titleColor);
    }

    @Override
    public boolean hasLargeDescription() {
        return false;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    /**
     * Override this method in your intel class.
     */
    protected DrawableIntelInfo getIntelInfo() {
        return new DrawableIntelInfo() {
            @Override
            public void draw(TooltipMakerAPI tooltip, Color bulletColor, Color titleColor) {
                tooltip.addPara("Implement 'getIntelInfo()' method to change this", 0);
            }
        };
    }
}
