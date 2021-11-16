package stelnet.board.query.view.list;

import java.util.LinkedList;
import java.util.List;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.board.query.Query;
import stelnet.util.StringUtils;
import uilib.RenderableComponent;
import uilib.property.Size;

public class QueryDescription extends RenderableComponent {

    private final boolean isLeftAligned;
    private final Query query;
    private final List<String> labels = new LinkedList<>();
    private final List<String> descriptions = new LinkedList<>();

    public QueryDescription(float width, Query query, boolean isLeftAligned) {
        this.isLeftAligned = isLeftAligned;
        this.query = query;
        buildLabelAndDescription();
        setSize(new Size(width, 50));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(6);
        addQueryDescription(tooltip);
    }

    private void addQueryDescription(TooltipMakerAPI tooltip) {
        String description = StringUtils.join(descriptions, "  ", "Invalid query");
        LabelAPI label = tooltip.addPara(description, 0);
        PositionAPI originalPosition = label.getPosition();
        boolean canReduce = canReduce(label, description);
        while (canReduce) {
            description = description.substring(0, description.length() - 1);
            label.setText(description + "...");
            canReduce = canReduce(label, description);
        }
        setAlignment(label);
        label.getPosition().setLocation(originalPosition.getX(), originalPosition.getY());
        label.setHighlightColor(Misc.getGrayColor());
        label.setHighlight(labels.toArray(new String[] {}));
    }

    private void buildLabelAndDescription() {
        String lines[] = query.toString().split("\\|");
        for (String line : lines) {
            String filter[] = line.split(":");
            if (filter.length == 2) {
                labels.add(filter[0] + ":");
            }
            descriptions.add(line);
        }
    }

    private boolean canReduce(LabelAPI label, String description) {
        boolean needsReducing = label.getPosition().getHeight() > 50;
        boolean canReduce = description.length() > 120;
        return needsReducing && canReduce;
    }

    private void setAlignment(LabelAPI label) {
        if (isLeftAligned) {
            label.setAlignment(Alignment.TL);
            return;
        }
        label.setAlignment(Alignment.TR);
    }
}
