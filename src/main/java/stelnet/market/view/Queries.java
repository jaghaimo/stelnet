package stelnet.market.view;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stelnet.market.IntelQuery;
import stelnet.ui.Group;
import stelnet.ui.Paragraph;
import stelnet.ui.Position;
import stelnet.ui.Renderable;
import stelnet.ui.Renderer;
import stelnet.ui.Size;

public class Queries implements Renderer {

    private List<IntelQuery> queries;

    public Queries(List<IntelQuery> queries) {
        this.queries = queries;
    }

    @Override
    public void render(CustomPanelAPI panel, Size size, Position position) {
        Size panelSize = size.getDifference(new Size(0, 38));
        Renderable queriesOrEmpty = get(panel, panelSize);
        queriesOrEmpty.setSize(panelSize);
        queriesOrEmpty.setScroller(true);
        queriesOrEmpty.render(panel, position.getX(), position.getY());
    }

    private Renderable get(CustomPanelAPI panel, Size size) {
        if (queries.isEmpty()) {
            return new Paragraph("No queries", size.getWidth());
        }
        return new Group(getRows(panel, size));
    }

    private List<Renderable> getRows(CustomPanelAPI panel, Size size) {
        List<Renderable> rows = new LinkedList<>();
        for (int i = 0; i < queries.size(); i++) {
            CustomPanelAPI rowPanel = panel.createCustomPanel(size.getWidth(), 24, null);
            Renderable queryRow = new QueryRow(size.getWidth(), i, queries);
            queryRow.render(rowPanel, 0, 0);
            rows.add(queryRow);
        }
        return rows;
    }
}
