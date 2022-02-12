package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.Query;
import uilib.RenderableComponent;
import uilib.UiConstants;
import uilib.property.Size;

public class QueryDescription extends RenderableComponent {

    private final Query query;
    private final float width;
    private final List<String[]> description = new LinkedList<>();
    private Color textColor;
    private Color labelColor;
    private final float labelWidth;
    private final float gridLineAdjustment = 2;
    private final float padding = UiConstants.DEFAULT_BUTTON_PADDING;
    private final float rowHeight = UiConstants.DEFAULT_BUTTON_HEIGHT - padding - gridLineAdjustment;

    public QueryDescription(float width, Query query) {
        this.width = width;
        this.query = query;
        labelWidth = Math.max(160, width - 1000);
        buildFilterDescription();
        setSize(new Size(width, gridLineAdjustment + description.size() * rowHeight));
        setColors();
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        float gridWidth = width;
        tooltip.addSpacer(2);
        tooltip.beginGridFlipped(gridWidth, 1, textColor, labelWidth, padding);
        addQueryDescription(tooltip);
        tooltip.addGrid(0);
    }

    private void addQueryDescription(TooltipMakerAPI tooltip) {
        float gridWidth = width;
        float labelWidthWithPadding = padding + labelWidth + padding;
        int row = 0;
        for (String[] filter : description) {
            if (filter.length != 2) {
                continue;
            }
            tooltip.addToGrid(
                0,
                row++,
                Misc.ucFirst(tooltip.shortenString(filter[1], gridWidth - labelWidthWithPadding)),
                filter[0],
                labelColor
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

    private void setColors() {
        textColor = Misc.getTextColor();
        labelColor = Misc.getGrayColor();
        if (!query.isEnabled()) {
            textColor = Misc.scaleAlpha(textColor, 0.3f);
            labelColor = Misc.scaleAlpha(labelColor, 0.2f);
        }
    }
}
