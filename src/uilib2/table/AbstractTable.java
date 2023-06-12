package uilib2.table;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractTable implements Table {

    @Override
    public UIComponentAPI draw(TooltipMakerAPI tooltip) {
        return addTable(tooltip);
    }
}
