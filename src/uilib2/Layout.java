package uilib2;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;

public interface Layout {
    public PositionAPI draw(CustomPanelAPI panel, float width, float height);
}
