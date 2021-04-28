package stelnet.market.view;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AllArgsConstructor;
import stelnet.market.IntelQuery;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Group;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

@AllArgsConstructor
public class Queries implements Renderable {

    private final Size size;
    private final List<IntelQuery> queries;

    @Override
    public void render(CustomPanelAPI panel) {
        if (queries.isEmpty()) {
            return;
        }
        Size panelSize = size.getDifference(new Size(0, 38));
        AbstractRenderable queries = new Group(getRows(panel, panelSize));
        queries.setSize(panelSize);
        queries.render(panel, 0, 38);
    }

    @Override
    public void render(TooltipMakerAPI info) {
    }

    private List<AbstractRenderable> getRows(CustomPanelAPI panel, Size size) {
        List<AbstractRenderable> rows = new LinkedList<>();
        for (int i = 0; i < queries.size(); i++) {
            CustomPanelAPI rowPanel = panel.createCustomPanel(size.getWidth(), 24, null);
            QueryRow queryRow = new QueryRow(size.getWidth(), i, queries);
            queryRow.render(rowPanel);
            rows.add(queryRow);
        }
        return rows;
    }
}
