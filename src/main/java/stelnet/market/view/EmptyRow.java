package stelnet.market.view;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import lombok.AllArgsConstructor;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

@AllArgsConstructor
public class EmptyRow {

    private boolean isEmpty;

    public void render(CustomPanelAPI panel, Size size) {
        if (isEmpty) {
            Size panelSize = size.getDifference(new Size(0, 38));
            Renderable paragraph = new Paragraph("No queries", size.getWidth());
            paragraph.setSize(panelSize);
            paragraph.setScroller(true);
            paragraph.render(panel, 0, 38);
        }
    }
}
