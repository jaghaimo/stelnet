package stelnet.market.data;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.market.view.AddQueryButton;
import stelnet.market.view.DeleteAllButton;
import stelnet.market.view.DeleteOneButton;
import stelnet.market.view.RefreshAllButton;
import stelnet.market.view.ToggleAllButton;
import stelnet.market.view.ToggleOneButton;
import stelnet.ui.CustomPanel;
import stelnet.ui.GridRenderer;
import stelnet.ui.Line;
import stelnet.ui.Padding;
import stelnet.ui.Paragraph;
import stelnet.ui.Renderable;
import stelnet.ui.Row;
import stelnet.ui.ScrollableStack;
import stelnet.ui.Size;
import stelnet.ui.Spacer;
import stelnet.ui.Stack;

public class MarketData {

    private CustomPanelAPI panel;
    private Size size;

    public MarketData(CustomPanelAPI panel, Size size) {
        this.panel = panel;
        this.size = size;
    }

    public Renderable get(List<IntelQuery> queries) {
        Renderable controlRow = getNewRow(//
                new Row(new AddQueryButton(), new RefreshAllButton(), new ToggleAllButton()), // left
                new Row(new DeleteAllButton(), new Padding(10)) // right
        );
        Renderable separator = new Line(size.getWidth(), Misc.getButtonTextColor());
        Renderable spacer = new Spacer(5);
        Renderable queryStack = new ScrollableStack(size.getDifference(new Size(0, 30)), getQueryRows(40));
        return new Stack(controlRow, separator, spacer, queryStack);
    }

    private Renderable getNewRow(Renderable left, Renderable right) {
        Size rowSize = new Size(size.getWidth(), 30);
        CustomPanelAPI rowPanel = getNewPanel(rowSize);
        GridRenderer renderer = new GridRenderer(rowSize);
        renderer.setTopLeft(left);
        renderer.setTopRight(right);
        renderer.render(rowPanel);
        return new CustomPanel(rowPanel, rowSize);
    }

    private List<Renderable> getQueryRows(int max) {
        List<Renderable> data = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            data.add(getNewRow(//
                    new Row(new Paragraph("Test", 200)), // left
                    new Row(new ToggleOneButton(true), new DeleteOneButton(), new Padding(10)) // right
            ));
        }
        return data;
    }

    private CustomPanelAPI getNewPanel(Size size) {
        return panel.createCustomPanel(size.getWidth(), size.getHeight(), null);
    }
}
