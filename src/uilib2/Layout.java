package uilib2;

import com.fs.starfarer.api.ui.CustomPanelAPI;

public interface Layout {
    /**
     * Create a new panel in x,y position.
     */
    public void create(CustomPanelAPI panel, float x, float y);
}
