package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipLocation;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class RowWithTooltip implements TableRow {

    private final TooltipCreator tc;
    private final TooltipLocation loc;

    @Setter
    private boolean recreateEveryFrame = false;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        tooltip.addTooltipToAddedRow(tc, loc, recreateEveryFrame);
    }
}
