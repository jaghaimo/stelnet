package stelnet.ui;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

public class VerticalGroup extends Group {

    public VerticalGroup(Renderable... elements) {
        super(Arrays.asList(elements));
    }

    public VerticalGroup(List<Renderable> elements) {
        super(elements);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        for (Renderable renderable : getElements()) {
            Size size = renderable.getSize();
            renderable.render(panel, x, y);
            y += size.getHeigth();
        }
    }
}
