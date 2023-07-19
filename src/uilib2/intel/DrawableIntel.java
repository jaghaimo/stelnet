package uilib2.intel;

import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import java.util.Set;

/**
 * DrawableIntel stub that only provides `createIntelInfo` implementation.
 * <p>
 * Your intel class will NEED to override either `hasLargeDescription` or `hasSmallDescription`.
 */
public abstract class DrawableIntel extends CallbackAwareIntel {

    @Override
    public void createIntelInfo(final TooltipMakerAPI info, final ListInfoMode mode) {
        final DrawableIntelInfo intelInfo = getIntelInfo();
        final Color bulletColor = getBulletColorForMode(mode);
        final Color titleColor = getTitleColor(mode);
        intelInfo.draw(info, bulletColor, titleColor);
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public boolean hasLargeDescription() {
        return false;
    }

    @Override
    public Set<String> getIntelTags(final SectorMapAPI map) {
        final Set<String> tags = super.getIntelTags(map);
        tags.add(getMainTag());
        return tags;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    protected abstract DrawableIntelInfo getIntelInfo();

    protected abstract String getMainTag();
}
