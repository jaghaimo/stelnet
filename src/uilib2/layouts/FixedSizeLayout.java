package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import uilib2.Layout;

public class FixedSizeLayout extends DecoratedLayout {

    private final float width;
    private final float height;

    public FixedSizeLayout(final Layout layout, final float width, final float height) {
        super(layout);
        this.width = width;
        this.height = height;
    }

    @Override
    public PositionAPI draw(final CustomPanelAPI panel, final float width, final float height) {
        return super.draw(panel, this.width, this.height);
    }
}
