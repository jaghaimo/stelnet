package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import stelnet.board.query.Query;
import uilib.RenderableComponent;
import uilib.property.Size;

public class QueryRow extends RenderableComponent {

    private final Query query;

    public QueryRow(float width, Query query) {
        this.query = query;
        setSize(new Size(width, 30));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        // TODO Auto-generated method stub

    }
}
