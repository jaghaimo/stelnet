package uilib2.layouts;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import lombok.RequiredArgsConstructor;
import uilib2.Layout;

@RequiredArgsConstructor
public abstract class DecoratedLayout implements Layout {

    private final Layout layout;

    @Override
    public PositionAPI draw(CustomPanelAPI panel, float width, float height) {
        return layout.draw(panel, width, height);
    }
}
