package stelnet.market_old.view;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AllArgsConstructor;
import stelnet.market_old.intel.IntelQuery;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Group;
import stelnet.ui.Renderable;
import stelnet.ui.property.Size;

@AllArgsConstructor
public class Queries implements Renderable {

    private final Size size;
    private final List<IntelQuery> queries;

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        if (queries.isEmpty()) {
            return;
        }
        Size panelSize = size.reduce(new Size(0, 38));
        AbstractRenderable queries = new Group(getRows(panel, panelSize));
        queries.setSize(panelSize);
        queries.render(panel, 0, 38);
    }

    @Override
    public void render(TooltipMakerAPI info) {
    }

    private List<Renderable> getRows(CustomPanelAPI panel, Size size) {
        List<Renderable> rows = new LinkedList<>();
        for (int i = 0; i < queries.size(); i++) {
            CustomPanelAPI rowPanel = panel.createCustomPanel(size.getWidth(), 24, null);
            QueryRow queryRow = new QueryRow(size.getWidth(), i, queries);
            queryRow.render(rowPanel, 0, 0);
            rows.add(queryRow);
        }
        return rows;
    }
}
