package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AccessLevel;
import lombok.Getter;
import stelnet.helper.LogHelper;

/**
 * Simple element grouping usable in both large and small intel.
 *
 * Extended by {@link Row} and {@link Stack} which can only be used in large
 * intel only.
 */

@Getter(AccessLevel.PROTECTED)
public class Group extends AbstractRenderable {

    private List<AbstractRenderable> elements;

    public Group(List<AbstractRenderable> elements) {
        this.elements = elements;
    }

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
        LogHelper.info("Setting calculated size");
        super.setSize(new Size(width, height));
    }
}
