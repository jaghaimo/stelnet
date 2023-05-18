package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicButton implements Button {

    private final String text;
    private final Object data;
    private final float width;
    private final float height;
    private final float pad;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        addButton(tooltip);
    }

    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        return tooltip.addButton(text, data, width, height, pad);
    }
}
