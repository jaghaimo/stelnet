package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import uilib2.Layout;

public class ResizedLayout extends DecoratedLayout {

    private final float width;
    private final float height;

    public ResizedLayout(final Layout layout, final float width, final float height) {
        super(layout);
        this.width = width;
        this.height = height;
    }

    @Override
    public PositionAPI draw(final CustomPanelAPI panel, final float width, final float height) {
        final PositionAPI position = super.draw(panel, width, height);
        position.setSize(this.width, this.height);
        return position;
    }
}
