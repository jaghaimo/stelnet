package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Button extends AbstractRenderable implements ButtonHandler {

    private String title;
    private Color color;
    private boolean isEnabled;
    private ButtonHandler handler;
    private CutStyle cutStyle;
    private int shortcut;

    public Button(Size size, String title, boolean isEnabled, Color color) {
        this.title = title;
        this.isEnabled = isEnabled;
        this.color = color;
        this.cutStyle = CutStyle.ALL;
        this.shortcut = 0;
        setSize(size);
        setWithScroller(false);
    }

    @Override
    public boolean hasPrompt() {
        if (handler != null) {
            return handler.hasPrompt();
        }
        return false;
    }

    @Override
    public void onCancel(IntelUIAPI ui) {
        if (handler != null) {
            handler.onCancel(ui);
        }
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        if (handler != null) {
            handler.onConfirm(ui);
        }
    }

    @Override
    public void onPrompt(TooltipMakerAPI tooltipMaker) {
        if (handler != null) {
            handler.onPrompt(tooltipMaker);
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Size size = getSize();
        Color foregroundColor = getColor();
        Color backgroundColor = Misc.scaleColor(foregroundColor, 0.5f);
        ButtonAPI button = tooltip.addButton(getTitle(), this, foregroundColor, backgroundColor, Alignment.MID,
                cutStyle, size.getWidth() - 4f, size.getHeight() - 4f, 4f);
        button.setEnabled(isEnabled);
        if (shortcut > 0) {
            button.setShortcut(shortcut, false);
        }
    }
}
