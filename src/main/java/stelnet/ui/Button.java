package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

public class Button extends Renderable implements Callable {

    private String title;
    private Color color;
    private boolean isEnabled;
    private Callable callback;
    private CutStyle cutStyle;
    private int shortcut;

    public Button(Size size, String title, boolean isEnabled, Color color) {
        this.title = title;
        this.isEnabled = isEnabled;
        this.color = color;
        this.cutStyle = CutStyle.ALL;
        this.shortcut = 0;
        setSize(size);
    }

    public String getTitle() {
        return title;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setCallback(Callable callback) {
        this.callback = callback;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCutStyle(CutStyle cutStyle) {
        this.cutStyle = cutStyle;
    }

    public void setShortcut(int shortcut) {
        this.shortcut = shortcut;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void cancel() {
        if (callback != null) {
            callback.cancel();
        }
    }

    @Override
    public void confirm(IntelUIAPI ui) {
        if (callback != null) {
            callback.confirm(ui);
        }
    }

    @Override
    public boolean hasPrompt() {
        if (callback != null) {
            return callback.hasPrompt();
        }
        return false;
    }

    @Override
    public void prompt(TooltipMakerAPI tooltipMaker) {
        if (callback != null) {
            callback.prompt(tooltipMaker);
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
