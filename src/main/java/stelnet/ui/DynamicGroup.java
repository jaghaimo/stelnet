package stelnet.ui;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

/**
 * Dynamically scalled group that extends up to desired width.
 * 
 * Adds elements horizontally until desired width, then starts from new line.
 * Only works for same height items (otherwise items may overlap).
 */
@AllArgsConstructor
@Getter
@Log4j
public class DynamicGroup extends AbstractRenderable {

    private float width;
    private Size size;
    private List<Renderable> elements;

    public DynamicGroup(Size size, Renderable... elements) {
        this.elements = Arrays.asList(elements);
    }

    @Override
    public Size getSize() {
        if (size == null) {
            calculateSize();
        }
        return size;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        Position offset = new Position();
        for (Renderable renderable : getElements()) {
            offset = getNewOffset(offset, renderable.getSize());
            renderable.render(panel, offset.getX(), offset.getY());
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        for (Renderable renderable : getElements()) {
            renderable.render(tooltip);
        }
    }

    private void calculateSize() {
        Position finalOffset = new Position();
        for (Renderable renderable : getElements()) {
            finalOffset = getNewOffset(finalOffset, renderable.getSize());
        }
        size = new Size(
                Math.max(width, finalOffset.getX()),
                finalOffset.getY()
        );
        log.debug("Calculated size as " + size);
    }

    private Position getNewOffset(Position startingPosition, Size shiftSize) {
        // TODO missing logic for shifting in x or y depending on desired width or
        // height
        return startingPosition.shift(shiftSize);
    }
}
