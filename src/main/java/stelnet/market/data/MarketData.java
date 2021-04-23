package stelnet.market.data;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.market.view.AddQueryButton;
import stelnet.market.view.DeleteAllButton;
import stelnet.market.view.DeleteOneButton;
import stelnet.market.view.ToggleAllButton;
import stelnet.market.view.ToggleOneButton;
import stelnet.market.view.UpdateAllButton;
import stelnet.ui.CustomPanel;
import stelnet.ui.GridData;
import stelnet.ui.GridRenderer;
import stelnet.ui.Line;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.Row;
import stelnet.ui.ScrollableStack;
import stelnet.ui.Size;
import stelnet.ui.Spacer;
import stelnet.ui.Stack;

public class MarketData implements GridData {

    private CustomPanelAPI panel;
    private List<IntelQuery> queries;

    public MarketData(CustomPanelAPI panel, List<IntelQuery> queries) {
        this.panel = panel;
        this.queries = queries;
    }

    @Override
    public Renderable getTopLeft(Size size) {
        Renderable controlRow = getControlRow(size);
        Renderable separator = new Line(size.getWidth(), Misc.getButtonTextColor());
        Renderable spacer = new Spacer(5);
        Renderable queryStack = getQueryStack(size);
        return new Stack(controlRow, separator, spacer, queryStack);
    }

    @Override
    public Renderable getTopRight(Size size) {
        return null;
    }

    @Override
    public Renderable getBottomLeft(Size size) {
        return null;
    }

    @Override
    public Renderable getBottomRight(Size size) {
        return null;
    }

    private Renderable getControlRow(Size size) {
        return getNewRow(//
                size, //
                new Row(new AddQueryButton(queries), new UpdateAllButton(queries), new ToggleAllButton(queries)), // left
                new Row(new DeleteAllButton(queries), new Spacer(10)) // right
        );
    }

    private Renderable getQueryStack(Size size) {
        if (queries.size() == 0) {
            return new Paragraph("No queries", 400);
        }
        return new ScrollableStack(size.getDifference(new Size(0, 30)), getQueryRows(size));
    }

    private Renderable getNewRow(Size size, Renderable left, Renderable right) {
        Size rowSize = new Size(size.getWidth(), 30);
        CustomPanelAPI rowPanel = getNewPanel(rowSize);
        GridRenderer renderer = new GridRenderer(rowSize);
        renderer.setTopLeft(left);
        renderer.setTopRight(right);
        renderer.render(rowPanel);
        return new CustomPanel(rowPanel, rowSize);
    }

    private List<Renderable> getQueryRows(Size size) {
        List<Renderable> data = new ArrayList<>();
        Size newSize = size.getDifference(new Size(200, 0));
        for (int i = 0; i < queries.size(); i++) {
            IntelQuery query = queries.get(i);
            data.add(getNewRow(//
                    size, //
                    new Row(new Stack(new Spacer(7), new Paragraph(query.getDescription(), newSize.getWidth()))), // left
                    new Row(new ToggleOneButton(query), new DeleteOneButton(queries, i), new Spacer(10)) // right
            ));
        }
        return data;
    }

    private CustomPanelAPI getNewPanel(Size size) {
        return panel.createCustomPanel(size.getWidth(), size.getHeight(), null);
    }
}
