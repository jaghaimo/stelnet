package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTable implements Table {

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addTable(tooltip);
    }
}
