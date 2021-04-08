package stelnet.commodity.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class Renderable {

    public void render(CustomPanelAPI panel, float x, float y) {
        Size size = getSize();
        TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeigth(), true);
        render(inner);
        panel.addUIElement(inner).inTL(x, y);
    }

    public abstract Size getSize();

    public abstract void render(TooltipMakerAPI tooltip);
}
