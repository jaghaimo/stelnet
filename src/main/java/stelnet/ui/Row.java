package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

public class Row extends Group {

    public Row(List<Renderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            x += size.getWidth();
        }
    }
}
