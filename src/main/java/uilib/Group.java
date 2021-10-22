package uilib;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import uilib.property.Size;

/**
 * Simple element grouping usable in both large and small intel.
 *
 * Extended by {@link HorizontalViewContainer} and {@link VerticalViewContainer}
 * which can be used in large intel only.
 */
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@Log4j
public class Group extends AbstractRenderable {

    private List<Renderable> elements;

    public Group(Renderable... elements) {
        this.elements = Arrays.asList(elements);
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
        for (Renderable renderable : getElements()) {
            renderable.render(tooltip);
        }
    }

    protected void setCalculatedSize() {
        float width = 0;
        float height = 0;
        for (Renderable renderable : elements) {
            Size size = renderable.getSize();
            width = Math.max(width, size.getWidth());
            height = Math.max(height, size.getHeight());
        }
        super.setSize(new Size(width, height));
        log.debug("Setting calculated size as " + getSize());
    }
}
