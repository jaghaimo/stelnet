package stelnet.market.view;

import java.util.List;

import stelnet.market.IntelQuery;
import stelnet.ui.CustomPanel;
import stelnet.ui.Position;
import stelnet.ui.Renderable;
import stelnet.ui.Row;
import stelnet.ui.Size;
import stelnet.ui.Stack;

public class QueryRow extends CustomPanel {

    private static final float rowHeight = 24;

    public QueryRow(float width, int i, List<IntelQuery> queries) {
        super(getAll(width, i, queries));
        setSize(getRowSize(width));
    }

    private static Renderable getAll(float width, int i, List<IntelQuery> queries) {
        return new Stack(getLeftRenderable(width, i, queries), getRightRenderable(width, i, queries));
    }

    private static Renderable getLeftRenderable(float width, int i, List<IntelQuery> queries) {
        IntelQuery query = queries.get(i);
        return new Row(new IntelDescription(query, width - 200));
    }

    private static Renderable getRightRenderable(float width, int i, List<IntelQuery> queries) {
        IntelQuery query = queries.get(i);
        Renderable rightColumn = new Row(new DeleteOneButton(queries, i), new ToggleOneButton(query));
        rightColumn.setSize(getRowSize(width));
        rightColumn.setOffset(new Position(0, -rowHeight));
        return rightColumn;
    }

    private static Size getRowSize(float width) {
        return new Size(width, rowHeight);
    }
}
