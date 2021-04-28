package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.Line;
import stelnet.ui.Position;
import stelnet.ui.Renderable;
import stelnet.ui.RenderableView;
import stelnet.ui.Row;
import stelnet.ui.Size;
import stelnet.ui.Stack;

public class ControlRowView implements RenderableView {

    private final List<IntelQuery> queries;

    public ControlRowView(List<IntelQuery> queries) {
        this.queries = queries;
    }

    @Override
    public void render(CustomPanelAPI panel, Size size) {
        Renderable controlRow = get(size);
        controlRow.render(panel);
    }

    private Renderable get(Size size) {
        Renderable leftRow = new Row(new AddQueryButton(queries), new UpdateAllButton(queries),
                new ToggleAllButton(queries));
        Renderable rightRow = new Row(new DeleteAllButton(queries));
        rightRow.setSize(new Size(size.getWidth(), 24));
        rightRow.setOffset(new Position(0, -24));
        Renderable separator = new Line(size.getWidth(), Misc.getButtonTextColor());
        separator.setOffset(new Position(0, -21));
        return new Stack(leftRow, rightRow, separator);
    }
}
