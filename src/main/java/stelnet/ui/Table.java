package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.AllArgsConstructor;
import stelnet.helper.GlobalHelper;

@AllArgsConstructor
public class Table extends AbstractRenderable {

    private final static int ROW_HEIGHT = 20;

    private final String title;
    private final float width;
    private final float maxHeight;
    private final TableContent tableContent;

    @Override
    public Size getSize() {
        float tableHeight = ROW_HEIGHT * (tableContent.getRows().size() + 1) + 3;
        return new Size(width, Math.min(maxHeight, tableHeight));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.beginTable(GlobalHelper.getPlayerFaction(), ROW_HEIGHT, tableContent.getHeaders(width));
        for (RowDataElement row : tableContent.getRows()) {
            tooltip.addRow(row.buildObjectArray());
        }
        tooltip.addTable(title, 0, 0);
    }
}
