package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import uilib2.Layout;

public class ResizedLayout extends DecoratedLayout {

    private final float width;
    private final float height;

    public ResizedLayout(Layout layout, float width, float height) {
        super(layout);
        this.width = width;
        this.height = height;
    }

    @Override
    public PositionAPI draw(CustomPanelAPI panel, float width, float height) {
        PositionAPI position = super.draw(panel, width, height);
        position.setSize(this.width, this.height);
        return position;
    }
}
