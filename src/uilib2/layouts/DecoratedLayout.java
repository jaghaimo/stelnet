package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import lombok.RequiredArgsConstructor;
import uilib2.Layout;

@RequiredArgsConstructor
public abstract class DecoratedLayout implements Layout {

    private final Layout layout;

    @Override
    public PositionAPI draw(final CustomPanelAPI panel, final float width, final float height) {
        return layout.draw(panel, width, height);
    }
}
