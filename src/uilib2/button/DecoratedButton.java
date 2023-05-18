package uilib2.button;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DecoratedButton implements Button {

    protected final Button button;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        button.draw(tooltip);
    }
}
