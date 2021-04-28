package stelnet.market.view;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.AllArgsConstructor;
import stelnet.market.IntelQuery;
import stelnet.ui.Line;
import stelnet.ui.Position;
import stelnet.ui.Renderable;
import stelnet.ui.Row;
import stelnet.ui.Size;
import stelnet.ui.Stack;

@AllArgsConstructor
public class ControlRow implements Renderable {

    private Size size;
    private List<IntelQuery> queries;

    @Override
    public void render(CustomPanelAPI panel) {
        Renderable renderable = makeRow();
        renderable.render(panel);
    }

    @Override
    public void render(TooltipMakerAPI tooltipMakerAPI) {
    }

    private Renderable makeRow() {
        Row leftRow = new Row(new AddQueryButton(queries), new UpdateAllButton(queries), new ToggleAllButton(queries));
        Row rightRow = new Row(new DeleteAllButton(queries));
        rightRow.setSize(new Size(size.getWidth(), 24));
        rightRow.setOffset(new Position(0, -24));
        Line separator = new Line(size.getWidth(), Misc.getButtonTextColor());
        separator.setOffset(new Position(0, -21));
        return new Stack(leftRow, rightRow, separator);
    }
}
