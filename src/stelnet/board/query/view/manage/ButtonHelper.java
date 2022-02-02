package stelnet.board.query.view.manage;

import java.awt.Color;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.view.FilteringButton;
import stelnet.util.ColorUtils;

@RequiredArgsConstructor
public class ButtonHelper {

    private final ManageResultsFactory factory;

    public void prepareDmods() {
        Color textColor = null;
        Color positiveColor = ColorUtils.positiveHighlight();
        Color negativeColor = ColorUtils.negativeHighlight();
        if (!factory.hasDmodSelection()) {
            textColor = ColorUtils.buttonText();
            positiveColor = negativeColor = ColorUtils.buttonBgDark();
        }
        prepareDmods(textColor, positiveColor, negativeColor);
    }

    private void prepareDmods(Color textColor, Color positiveColor, Color negativeColor) {
        for (FilteringButton button : factory.getDModAllowed()) {
            Color desiredColor = getDesiredColor(button, positiveColor, negativeColor);
            float desiredScale = getDesiredScale(button);
            prepareDmods(button, textColor, desiredColor, desiredScale);
        }
    }

    private void prepareDmods(FilteringButton button, Color textColor, Color desiredColor, float desiredScale) {
        button.setTextColor(desiredColor);
        button.setBackgroundColor(desiredColor);
        if (textColor == null) {
            button.scaleTextColor(desiredScale);
            button.scaleBackground(desiredScale * 0.5f);
        } else {
            button.setTextColor(textColor);
        }
    }

    private Color getDesiredColor(FilteringButton button, Color positiveColor, Color negativeColor) {
        if (button.isStateOn()) {
            return positiveColor;
        }
        return negativeColor;
    }

    private float getDesiredScale(FilteringButton button) {
        if (button.isStateOn()) {
            return 1.0f;
        }
        return 0.7f;
    }
}
