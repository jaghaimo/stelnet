package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import uilib.property.Position;
import uilib.property.Size;

/**
 * Dynamically scaled group that extends up to desired width.
 *
 * Adds elements horizontally until desired width, then starts from new line.
 * Only works for same height items (otherwise items may overlap).
 */
@RequiredArgsConstructor
@Getter
@Log4j
public class DynamicGroup extends AbstractRenderable {

    private final float width;
    private final List<Renderable> elements;

    public DynamicGroup(float width, Renderable... elements) {
        this(width, Arrays.asList(elements));
    }

    @Override
    public Size getSize() {
        if (super.getSize() == null) {
            calculateSize();
        }
        return super.getSize();
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        Position offset = new Position(x, y);
        for (Renderable renderable : getElements()) {
            // the element might not fit
            offset = verifyOffset(offset, renderable.getSize(), x);
            renderable.render(panel, offset.getX(), offset.getY());
            // set offset for next element
            offset = advanceOffset(offset, renderable.getSize(), x);
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        for (Renderable renderable : getElements()) {
            renderable.render(tooltip);
        }
    }

    private void calculateSize() {
        Position finalOffset = new Position(0, 0);
        Size finalSize = new Size(0, 0);
        for (Renderable renderable : getElements()) {
            finalOffset = advanceOffset(finalOffset, renderable.getSize(), 0);
            finalSize = renderable.getSize();
        }
        Size size = new Size(Math.max(width, finalOffset.getX()), finalOffset.getY() + finalSize.getHeight());
        log.debug("Calculated size as " + size);
        super.setSize(size);
    }

    private Position verifyOffset(Position startingPosition, Size elementSize, float startX) {
        Position elementEnds = startingPosition.shift(elementSize);
        if (elementEnds.getX() > startX + width) {
            return new Position(startX, elementEnds.getY());
        }
        return startingPosition;
    }

    private Position advanceOffset(Position startingPosition, Size shiftSize, float startX) {
        Position newPosition = startingPosition.shift(shiftSize);
        if (newPosition.getX() > startX + width) {
            return new Position(startX, newPosition.getY());
        }
        return new Position(newPosition.getX(), startingPosition.getY());
    }
}
