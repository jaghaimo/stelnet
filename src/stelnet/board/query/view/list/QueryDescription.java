package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.Query;
import uilib.RenderableComponent;
import uilib.property.Size;

public class QueryDescription extends RenderableComponent {

    private final Query query;
    private final float width;
    private final List<String[]> description = new LinkedList<>();

    private final float ROW_HEIGHT = 18;
    private final float LABEL_WIDTH;
    private final float PADDING = 5;

    public QueryDescription(float width, Query query) {
        this.width = width;
        this.query = query;
        LABEL_WIDTH = Math.max(120, width - 650);
        buildFilterDescription();
        setSize(new Size(width, PADDING + description.size() * ROW_HEIGHT));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        float gridWidth = width;
        tooltip.addSpacer(6);
        tooltip.beginGridFlipped(gridWidth, 1, Misc.getTextColor(), LABEL_WIDTH, PADDING);
        addQueryDescription(tooltip, query.toString());
        tooltip.addGrid(0);
    }

    private void addQueryDescription(TooltipMakerAPI tooltip, String queryToString) {
        float gridWidth = width;
        float labelWidth = PADDING + LABEL_WIDTH + PADDING;
        int row = 0;
        for (String[] filter : description) {
            if (filter.length != 2) {
                continue;
            }
            tooltip.addToGrid(
                0,
                row++,
                tooltip.shortenString(filter[1], gridWidth - labelWidth),
                filter[0],
                Misc.getGrayColor()
            );
        }
    }

    private void buildFilterDescription() {
        String lines[] = query.toString().split("\\|");
        for (String line : lines) {
            String filter[] = line.split(":");
            if (filter.length != 2) {
                continue;
            }
            description.add(filter);
        }
    }
}
