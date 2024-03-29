package stelnet.board.query.view.manage;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilterAwareFactory;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.board.query.view.SizeHelper;
import stelnet.util.ColorHelper;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

public class ManageResultsFactory extends FilterAwareFactory implements RenderableFactory {

    private final FilteringButton[] dModCount;
    private final FilteringButton[] dModAllowed;
    private final GroupByButton[] groupingButtons;
    private final Button[] submarketButtons;
    private final Button[] otherButtons;

    public ManageResultsFactory(QueryManager manager) {
        dModCount = ButtonUtils.getDModsCount(manager);
        dModAllowed = ButtonUtils.getDMods(manager);
        groupingButtons = ButtonUtils.getGroupingButtons(manager);
        submarketButtons = ButtonUtils.getSubmarketButtons(manager);
        otherButtons = ButtonUtils.getOtherButtons(manager);
    }

    @Override
    public List<Renderable> create(Size size) {
        prepareButtons(dModCount);
        prepareButtons(dModAllowed);
        SizeHelper sizeHelper = new SizeHelper(size);
        sizeHelper.movePartition(100);
        List<Renderable> elements = new LinkedList<>();
        elements.add(new Spacer(-2 * UiConstants.DEFAULT_SPACER));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANAGE_GROUPING_SORTING, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_RESULT_GROUPING, groupingButtons, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANAGE_FILTERING, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_FILTERING_SUBMARKET, submarketButtons, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_FILTERING_OTHER, otherButtons, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANAGE_DMODS, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_DMOD_COUNT, dModCount));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MANAGE_DMOD_SET, dModAllowed));
        return elements;
    }

    private void prepareButtons(FilteringButton[] buttonsToPrepare) {
        Color textColor = ColorHelper.basePlayerColor();
        Color backgroundColor = ColorHelper.darkPlayerColor();
        for (FilteringButton button : buttonsToPrepare) {
            button.setTextColor(textColor);
            button.setBackgroundColor(backgroundColor);
            prepareButton(button);
        }
    }

    private void prepareButton(FilteringButton button) {
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
