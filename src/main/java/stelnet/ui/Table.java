package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.GlobalHelper;

public class Table extends Renderable {

    private final static int ROW_HEIGHT = 20;

    private String title;
    private float width;
    private float maxHeight;
    private TableContent tableContent;

    public Table(String title, float width, float maxHeight, TableContent tableContent) {
        this.title = title;
        this.width = width;
        this.maxHeight = maxHeight;
        this.tableContent = tableContent;
    }

    @Override
    public Size getSize() {
        float tableHeight = ROW_HEIGHT * (tableContent.getRows().size() + 1) + 3;
        return new Size(width, Math.min(maxHeight, tableHeight));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.beginTable(GlobalHelper.getPlayerFaction(), ROW_HEIGHT, tableContent.getHeaders(width));
        for (Object[] row : tableContent.getRows()) {
            tooltip.addRow(row);
        }
        tooltip.addTable(title, 0, 0);
    }
}
