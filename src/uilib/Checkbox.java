package uilib;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.Getter;
import lombok.Setter;
import uilib.property.Size;

@Getter
@Setter
public class Checkbox extends RenderableComponent {

    private String text;
    private UICheckboxSize checkboxSize = UICheckboxSize.SMALL;
    private boolean isChecked = true;
    private boolean isEnabled = true;

    public Checkbox(String text, Size size) {
        setText(text);
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Size size = getSize();
        ButtonAPI button = tooltip.addCheckbox(size.getWidth(), size.getHeight(), text, checkboxSize, 0);
        button.setChecked(isChecked);
        button.setEnabled(isEnabled);
    }
}
