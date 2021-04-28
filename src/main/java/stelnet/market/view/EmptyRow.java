package stelnet.market.view;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AllArgsConstructor;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Paragraph;
import stelnet.ui.Position;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

@AllArgsConstructor
public class EmptyRow implements Renderable {

    private final Size size;
    private final boolean isEmpty;

    @Override
    public void render(CustomPanelAPI panel) {
        if (isEmpty) {
            Size panelSize = size.getDifference(new Size(0, 38));
            AbstractRenderable paragraph = new Paragraph("No queries", size.getWidth());
            paragraph.setSize(panelSize);
            paragraph.setWithScroller(true);
            paragraph.setOffset(new Position(0, 38));
            paragraph.render(panel);
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltipMakerAPI) {
    }
}
