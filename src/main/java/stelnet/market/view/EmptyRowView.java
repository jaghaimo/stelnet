package stelnet.market.view;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import lombok.AllArgsConstructor;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Paragraph;
import stelnet.ui.RenderableView;
import stelnet.ui.Size;

@AllArgsConstructor
public class EmptyRowView implements RenderableView {

    private final boolean isEmpty;

    @Override
    public void render(CustomPanelAPI panel, Size size) {
        if (isEmpty) {
            Size panelSize = size.getDifference(new Size(0, 38));
            AbstractRenderable paragraph = new Paragraph("There are no Intel queries yet.", size.getWidth());
            paragraph.setSize(panelSize);
            paragraph.render(panel, 0, 38);
        }
    }
}
