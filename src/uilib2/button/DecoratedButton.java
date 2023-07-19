package uilib2.button;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DecoratedButton extends AbstractButton {

    private final Button button;

    public ButtonAPI addButton(TooltipMakerAPI tooltip) {
        return button.addButton(tooltip);
    }
}
