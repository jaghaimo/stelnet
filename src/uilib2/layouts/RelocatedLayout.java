package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import uilib2.Layout;

public class RelocatedLayout extends DecoratedLayout {

    private final float x;
    private final float y;

    public RelocatedLayout(final Layout layout, final float x, final float y) {
        super(layout);
        this.x = x;
        this.y = y;
    }

    @Override
    public PositionAPI draw(final CustomPanelAPI panel, final float width, final float height) {
        final PositionAPI position = super.draw(panel, width, height);
        position.inTL(x, y);
        return position;
    }
}
