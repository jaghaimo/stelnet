package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import uilib.property.Position;
import uilib.property.Size;

public interface Renderable {
    public Position getOffset();

    public Size getSize();

    public void render(CustomPanelAPI panel, float x, float y);

    public void render(TooltipMakerAPI tooltip);
}
