package stelnet.market.view;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.AllArgsConstructor;
import stelnet.market.IntelQuery;
import stelnet.ui.*;

import java.util.List;

@AllArgsConstructor
public class ControlRow implements Renderable {

    private final Size size;
    private final List<IntelQuery> queries;

    @Override
    public void render(CustomPanelAPI panel) {
        AbstractRenderable controlRow = get(size);
        controlRow.render(panel);
    }

    @Override
    public void render(TooltipMakerAPI info) {
    }

    private AbstractRenderable get(Size size) {
        AbstractRenderable leftRow = new HorizontalViewContainer(new AddQueryButton(queries), new UpdateAllButton(queries),
                new ToggleAllButton(queries));
        AbstractRenderable rightRow = new HorizontalViewContainer(new DeleteAllButton(queries));
        rightRow.setSize(new Size(size.getWidth(), 24));
        rightRow.setOffset(new Position(0, -24));
        AbstractRenderable separator = new Line(size.getWidth(), Misc.getButtonTextColor());
        separator.setOffset(new Position(0, -21));
        return new VerticalViewContainer(leftRow, rightRow, separator);
    }
}
