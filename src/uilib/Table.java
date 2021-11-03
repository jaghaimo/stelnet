package uilib;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.util.SectorUtils;
import uilib.property.Size;

@RequiredArgsConstructor
@Setter
public class Table extends RenderableComponent {

    private static final int ROW_HEIGHT = 20;

    private final String title;
    private final float width;
    private final float maxHeight;
    private final TableContent tableContent;

    private FactionAPI faction = SectorUtils.getPlayerFaction();
    private String noRowsDescription = "No rows present.";

    @Override
    public Size getSize() {
        float tableHeight = ROW_HEIGHT * (tableContent.getRows().size() + 1) + 3;
        float height = maxHeight == 0 ? tableHeight : Math.min(maxHeight, tableHeight);
        return new Size(width, height);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        boolean hasRows = false;
        tooltip.beginTable(faction, ROW_HEIGHT, tableContent.getHeaders(width));
        for (TableContentRow row : tableContent.getRows()) {
            tooltip.addRow(row.buildObjectArray());
            hasRows = true;
        }
        if (hasRows) {
            tooltip.addTable(title, 0, 0);
        } else {
            tooltip.addPara(noRowsDescription, 0);
        }
    }
}
