package uilib2.table;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIPanelAPI;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableCustom extends AbstractTable {

    private final Color base;
    private final Color dark;
    private final Color bright;
    private final float itemHeight;
    private final boolean withBorder;
    private final boolean withHeader;
    private final Object[] columns;
    private final String emptyText;
    private final int andMore;
    private final float pad;

    @Getter
    private final List<TableRow> rows = new LinkedList<>();

    public TableCustom(
        final FactionAPI faction,
        final float itemHeight,
        final boolean withBorder,
        final boolean withHeader,
        final Object[] columns,
        final String emptyText,
        final int andMore,
        final float pad
    ) {
        this(
            faction.getBaseUIColor(),
            faction.getDarkUIColor(),
            faction.getBrightUIColor(),
            itemHeight,
            withBorder,
            withHeader,
            columns,
            emptyText,
            andMore,
            pad
        );
    }

    @Override
    public UIPanelAPI addTable(final TooltipMakerAPI tooltip) {
        final UIPanelAPI table = tooltip.beginTable(base, dark, bright, itemHeight, withBorder, withHeader, columns);
        for (final TableRow row : rows) {
            row.draw(tooltip);
        }
        tooltip.addTable(emptyText, andMore, pad);
        return table;
    }
}
