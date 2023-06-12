package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import uilib2.Layout;

public class RelocatedLayout extends DecoratedLayout {

    private final float x;
    private final float y;

    public RelocatedLayout(Layout layout, float x, float y) {
        super(layout);
        this.x = x;
        this.y = y;
    }

    @Override
    public PositionAPI draw(CustomPanelAPI panel, float width, float height) {
        PositionAPI position = super.draw(panel, width, height);
        position.inTL(x, y);
        return position;
    }
}
