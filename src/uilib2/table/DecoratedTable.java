package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIPanelAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoratedTable extends AbstractTable {

    private final Table table;

    public UIPanelAPI addTable(TooltipMakerAPI tooltip) {
        return table.addTable(tooltip);
    }
}
