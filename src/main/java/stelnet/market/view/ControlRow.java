package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.AllArgsConstructor;
import stelnet.market.IntelQuery;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.HorizontalViewContainer;
import stelnet.ui.Line;
import stelnet.ui.Renderable;
import stelnet.ui.VerticalViewContainer;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

@AllArgsConstructor
public class ControlRow implements Renderable {

    private final Size size;
    private final List<IntelQuery> queries;

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        Renderable controlRow = get(size);
        controlRow.render(panel, 0, 0);
    }

    @Override
    public void render(TooltipMakerAPI info) {
    }

    private Renderable get(Size size) {
        AbstractRenderable leftRow = new HorizontalViewContainer(new AddQueryButton(queries),
                new UpdateAllButton(queries),
                new ToggleAllButton(queries));
        AbstractRenderable rightRow = new HorizontalViewContainer(new DeleteAllButton(queries));
        rightRow.setSize(new Size(size.getWidth(), 24));
        rightRow.setOffset(new Position(0, -24));
        AbstractRenderable separator = new Line(size.getWidth(), Misc.getButtonTextColor());
        separator.setOffset(new Position(0, -21));
        return new VerticalViewContainer(leftRow, rightRow, separator);
    }
}
