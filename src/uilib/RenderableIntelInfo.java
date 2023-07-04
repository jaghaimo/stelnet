package uilib;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;

public interface RenderableIntelInfo {
    public void render(TooltipMakerAPI info, Color bulletColor, Color titleColor);
}
