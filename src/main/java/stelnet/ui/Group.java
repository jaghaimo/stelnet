package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import stelnet.helper.LogHelper;

/**
 * Simple element grouping usable in both large and small intel.
 *
 * Extended by {@link HorizontalViewContainer} and {@link VerticalViewContainer} which can only be used in large
 * intel only.
 */

@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class Group extends AbstractRenderable {

    private List<AbstractRenderable> elements;

    @Override
    public Size getSize() {
        if (super.getSize() == null) {
            setCalculatedSize();
        }
        return super.getSize();
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        for (AbstractRenderable renderable : getElements()) {
            renderable.render(tooltip);
        }
    }

    protected void setCalculatedSize() {
        float width = 0;
        float height = 0;
        for (AbstractRenderable renderable : elements) {
            Size size = renderable.getSize();
            width = Math.max(width, size.getWidth());
            height = Math.max(height, size.getHeight());
        }
        super.setSize(new Size(width, height));
        LogHelper.debug("Setting calculated size as " + getSize());
    }
}
