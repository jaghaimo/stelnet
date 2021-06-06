package stelnet.market.view;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AllArgsConstructor;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

@AllArgsConstructor
public class EmptyRow implements Renderable {

    private final Size size;
    private final boolean isEmpty;

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        if (isEmpty) {
            Size panelSize = size.getDifference(new Size(0, 38));
            AbstractRenderable paragraph = new Paragraph("There are no Intel queries yet.", size.getWidth());
            paragraph.setSize(panelSize);
            paragraph.render(panel, 0, 38);
        }
    }

    @Override
    public void render(TooltipMakerAPI info) {
    }
}
