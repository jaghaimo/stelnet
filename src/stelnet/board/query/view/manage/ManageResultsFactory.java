package stelnet.board.query.view.manage;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.QueryManager;
import stelnet.board.query.view.*;
import stelnet.util.L10n;
import uilib.*;
import uilib.Button;
import uilib.property.Size;

public class ManageResultsFactory extends FilterAwareFactory implements RenderableFactory {

    private final FilteringButton[] dModCount;
    private final FilteringButton[] dModAllowed;
    private final GroupByButton[] groupingButtons;
    private final Button[] submarketButtons;
    private final Button[] otherButtons;

    public ManageResultsFactory(final QueryManager manager) {
        dModCount = ButtonUtils.getDModsCount(manager);
        dModAllowed = ButtonUtils.getDMods(manager);
        groupingButtons = ButtonUtils.getGroupingButtons(manager);
        submarketButtons = ButtonUtils.getSubmarketButtons(manager);
        otherButtons = ButtonUtils.getOtherButtons(manager);
    }

    @Override
    public List<Renderable> create(final Size size) {
        prepareButtons(dModCount);
        prepareButtons(dModAllowed);
        final SizeHelper sizeHelper = new SizeHelper(size);
        sizeHelper.movePartition(100);
        final List<Renderable> elements = new LinkedList<>();
        elements.add(new Spacer(-2 * UiConstants.DEFAULT_SPACER));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), L10n.query("MANAGE_GROUPING_SORTING"), true));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MANAGE_RESULT_GROUPING"), groupingButtons, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), L10n.query("MANAGE_FILTERING"), true));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MANAGE_FILTERING_SUBMARKET"), submarketButtons, true));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MANAGE_FILTERING_OTHER"), otherButtons, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), L10n.query("MANAGE_DMODS"), true));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MANAGE_DMOD_COUNT"), dModCount));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MANAGE_DMOD_SET"), dModAllowed));
        return elements;
    }

    private void prepareButtons(final FilteringButton[] buttonsToPrepare) {
        final Color textColor = ColorHelper.basePlayerColor();
        final Color backgroundColor = ColorHelper.darkPlayerColor();
        for (final FilteringButton button : buttonsToPrepare) {
            button.setTextColor(textColor);
            button.setBackgroundColor(backgroundColor);
            prepareButton(button);
        }
    }

    private void prepareButton(final FilteringButton button) {
        float textScale = 1.0f;
        float backgroundScale = 1.0f;
        if (button.isStateOn()) {
            textScale = 0.5f;
            backgroundScale = 0;
        }
        button.scaleTextColor(textScale);
        button.scaleBackground(backgroundScale);
    }
}
