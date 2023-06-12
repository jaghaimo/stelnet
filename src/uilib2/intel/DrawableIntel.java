package uilib2.intel;

import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import java.util.Set;

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
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(getMainTag());
        return tags;
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

    protected abstract DrawableIntelInfo getIntelInfo();

    protected abstract String getMainTag();
}
