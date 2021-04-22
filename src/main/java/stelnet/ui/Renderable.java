package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;

public abstract class Renderable {

    public void render(CustomPanelAPI panel, float x, float y) {
        Size size = getSize();
        TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeight(), true);
        render(inner);
        panel.addUIElement(inner).inTL(x, y);
        log(new Position(x, y));
    }

    public abstract Size getSize();

    public abstract void render(TooltipMakerAPI tooltip);

    public void log() {
        LogHelper.debug(String.format("Rendered %s", this));
    }

    public void log(Position position) {
        LogHelper.debug(String.format("Rendered %s in %s", this, position));
    }
}
