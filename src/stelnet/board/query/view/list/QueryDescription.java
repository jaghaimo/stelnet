package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import uilib.RenderableComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryDescription extends RenderableComponent {

    private final Query query;

    public QueryDescription(Size size, Query query) {
        this(query);
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        float gridWidth = getSize().getWidth();
        float labelWidth = 150;
        tooltip.beginGridFlipped(gridWidth, 1, Misc.getTextColor(), labelWidth, 10);
        addQueryDescription(tooltip, query.toString());
        tooltip.addGrid(0);
    }

    private void addQueryDescription(TooltipMakerAPI tooltip, String queryToString) {
        float gridWidth = getSize().getWidth();
        float labelWidth = 150 + 20;
        String lines[] = queryToString.split("\\|");
        int row = 0;
        for (String line : lines) {
            String filter[] = line.split(":");
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
}
