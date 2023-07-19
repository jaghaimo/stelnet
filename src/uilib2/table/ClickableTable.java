package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIPanelAPI;

public class ClickableTable extends DecoratedTable {

    public ClickableTable(Table table) {
        super(table);
    }

    @Override
    public UIPanelAPI addTable(TooltipMakerAPI tooltip) {
        UIPanelAPI table = super.addTable(tooltip);
        tooltip.makeTableItemsClickable();
        return table;
    }
}
