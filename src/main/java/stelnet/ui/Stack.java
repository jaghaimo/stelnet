package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Stack extends Group {

    private Size size;

    public Stack(Size size, List<Renderable> elements) {
        super(elements);
        this.size = size;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        TooltipMakerAPI inner = panel.createUIElement(size.getWidth(), size.getHeigth(), true);
        for (Renderable renderable : getElements()) {
            renderable.render(inner);
        }
    }
}
