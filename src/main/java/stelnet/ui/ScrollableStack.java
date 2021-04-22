package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

/**
 * Renders elements in a vertical line.
 *
 * Has fixed size, will display a scroll if elements exceed it.
 */
public class ScrollableStack extends Group {

    private Size size;

    public ScrollableStack(Size size, List<Renderable> elements) {
        super(elements);
        this.size = size;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeight(), true);
        render(inner);
        panel.addUIElement(inner).inTL(x, y);
        log(new Position(x, y));
    }

    @Override
    public String toString() {
        return String.format("ScrollableStack(%d) with %s", getElements().size(), getSize());
    }
}
