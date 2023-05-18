package uilib2.intel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j;
import uilib2.Drawable;

@Log4j
public abstract class DrawableIntel extends CallbackAwareIntel {

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        DrawableIntelInfo intelInfo = getIntelInfo();
        Color bulletColor = getBulletColorForMode(mode);
        Color titleColor = getTitleColor(mode);
        intelInfo.draw(info, bulletColor, titleColor);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI tooltip, float width, float height) {
        long startTime = System.currentTimeMillis();
        for (Drawable drawable : getDrawableList(width, height)) {
            drawable.draw(tooltip);
        }
        long stopTime = System.currentTimeMillis();
        log.debug(String.format("Created small intel in %dms", stopTime - startTime));
    }

    @Override
    public boolean hasLargeDescription() {
        return false;
    }

    @Override
    public boolean hasSmallDescription() {
        return true;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    protected List<Drawable> getDrawableList(float width, float height) {
        return Collections.<Drawable>emptyList();
    }

    protected DrawableIntelInfo getIntelInfo() {
        return new DrawableIntelInfo() {
            @Override
            public void draw(TooltipMakerAPI tooltip, Color bulletColor, Color titleColor) {
                tooltip.addPara("Unimplemented method 'getIntelInfo'", 0);
            }
        };
    }
}
