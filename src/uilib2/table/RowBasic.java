package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RowBasic implements TableRow {

    private final Object[] data;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        tooltip.addRow(data);
    }
}
