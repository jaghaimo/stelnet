package stelnet.market.view;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stelnet.market.IntelQuery;
import stelnet.ui.Group;
import stelnet.ui.Renderable;
import stelnet.ui.RenderableView;
import stelnet.ui.Size;

public class QueriesView implements RenderableView {

    private List<IntelQuery> queries;

    public QueriesView(List<IntelQuery> queries) {
        this.queries = queries;
    }

    @Override
    public void render(CustomPanelAPI panel, Size size) {
        if (queries.isEmpty()) {
            return;
        }
        Size panelSize = size.getDifference(new Size(0, 38));
        Renderable queries = new Group(getRows(panel, panelSize));
        queries.setSize(panelSize);
        queries.setScroller(true);
        queries.render(panel, 0, 38);
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
