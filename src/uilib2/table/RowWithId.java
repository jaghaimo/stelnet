package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RowWithId implements TableRow {

    private final Object id;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        tooltip.setIdForAddedRow(id);
    }
}
