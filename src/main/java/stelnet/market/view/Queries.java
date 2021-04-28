package stelnet.market.view;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AllArgsConstructor;
import stelnet.market.IntelQuery;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Group;
import stelnet.ui.Position;
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
        AbstractRenderable queries = new Group(makeRows(panel, panelSize));
        queries.setSize(panelSize);
        queries.setWithScroller(true);
        queries.setOffset(new Position(0, 38));
        queries.render(panel);
    }

    @Override
    public void render(TooltipMakerAPI tooltipMakerAPI) {
    }

    private List<AbstractRenderable> makeRows(CustomPanelAPI panel, Size size) {
        List<AbstractRenderable> rows = new LinkedList<>();
        for (int i = 0; i < queries.size(); i++) {
            CustomPanelAPI rowPanel = panel.createCustomPanel(size.getWidth(), 24, null);
            AbstractRenderable queryRow = new QueryRow(size.getWidth(), i, queries);
            queryRow.render(rowPanel);
            rows.add(queryRow);
        }
        return rows;
    }
}
