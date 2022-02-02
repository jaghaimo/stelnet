package stelnet.board.query.view.manage;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilterAwareFactory;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.board.query.view.SizeHelper;
import stelnet.filter.Filter;
import stelnet.util.ColorUtils;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

public class ManageResultsFactory extends FilterAwareFactory implements RenderableFactory {

    private final FilteringButton[] dModCount;
    private final FilteringButton[] dModAllowed;
    private final Button[] groupingButtons;
    private final Button[] submarketButtons;
    private final Button[] otherButtons;

    public ManageResultsFactory(QueryManager manager) {
        dModCount = ButtonUtils.getDModsCount();
        dModAllowed = ButtonUtils.getDMods();
        groupingButtons = ButtonUtils.getGroupingButtons(manager);
        submarketButtons = ButtonUtils.getSubmarketButtons(manager);
        otherButtons = ButtonUtils.getOtherButtons(manager);
    }

    @Override
    public List<Renderable> create(Size size) {
        prepareDmods();
        SizeHelper sizeHelper = new SizeHelper(size);
        sizeHelper.movePartition(100);
        List<Renderable> elements = new LinkedList<>();
        elements.add(new Spacer(-2 * UiConstants.DEFAULT_SPACER));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANAGE_GROUPING_SORTING, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_RESULT_GROUPING, groupingButtons, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANAGE_FILTERING, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_FILTERING_SUBMARKET, submarketButtons, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_FILTERING_OTHER, otherButtons, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANAGE_DMODS, true, dModAllowed));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_DMOD_COUNT, dModCount, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_DMOD_SET, dModAllowed));
        return elements;
    }

    private boolean hasDmodSelection() {
        Set<Filter> allowedDmods = getFilters(dModAllowed, true);
        Set<Filter> disallowedDmods = getFilters(dModAllowed, false);
        return !allowedDmods.isEmpty() && !disallowedDmods.isEmpty();
    }

    private void prepareDmods() {
        Color textColor = null;
        Color positiveColor = ColorUtils.positiveHighlight();
        Color negativeColor = ColorUtils.negativeHighlight();
        if (!hasDmodSelection()) {
            textColor = ColorUtils.buttonText();
            positiveColor = negativeColor = ColorUtils.buttonBgDark();
        }
        prepareDmods(textColor, positiveColor, negativeColor);
    }

    private void prepareDmods(Color textColor, Color positiveColor, Color negativeColor) {
        for (FilteringButton button : dModAllowed) {
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
