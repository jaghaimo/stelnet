package stelnet.board.query.view.add;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.filter.Filter;

@RequiredArgsConstructor
public class ShipButtonHelper {

    private final ShipQueryFactory factory;

    public void prepareBuiltIns() {
        Set<Filter> filters = factory.getCommonFilters();
        Set<String> hullModIds = factory.getProvider().getBuiltInIds(filters);
        hullModIds.add("None");
        for (FilteringButton button : factory.getBuiltIns()) {
            button.updateVisibility(hullModIds);
        }
    }

    public void prepareDmods() {
        Color textColor = null;
        Color positiveColor = Misc.getPositiveHighlightColor();
        Color negativeColor = Misc.getNegativeHighlightColor();
        if (!factory.hasDmodSelection()) {
            textColor = Misc.getButtonTextColor();
            positiveColor = negativeColor = Global.getSettings().getColor("buttonBgDark");
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
