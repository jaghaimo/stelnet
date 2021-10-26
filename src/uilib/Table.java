package uilib;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.AllArgsConstructor;
import stelnet.util.Sector;
import uilib.property.Size;

@AllArgsConstructor
public class Table extends AbstractRenderable {

    private static final int ROW_HEIGHT = 20;

    private final String title;
    private final float width;
    private final float maxHeight;
    private final TableContent tableContent;

    @Override
    public Size getSize() {
        float tableHeight = ROW_HEIGHT * (tableContent.getRows().size() + 1) + 3;
        float height = maxHeight == 0 ? tableHeight : Math.min(maxHeight, tableHeight);
        return new Size(width, height);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        boolean hasRows = false;
        // TODO : Externalise faction
        tooltip.beginTable(Sector.getPlayerFaction(), ROW_HEIGHT, tableContent.getHeaders(width));
        for (TableContentRow row : tableContent.getRows()) {
            tooltip.addRow(row.buildObjectArray());
            hasRows = true;
        }
        if (hasRows) {
            tooltip.addTable(title, 0, 0);
        } else {
            tooltip.addPara("No rows present.", 0);
        }
    }
}
