package stelnet.board.query.view;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import stelnet.util.ColorHelper;
import stelnet.util.L10n;
import uilib.Button;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.UiConstants;

public class ButtonGroup extends HorizontalViewContainer {

    public ButtonGroup(SizeHelper helper, Enum<?> label, Button[] buttons) {
        super();
        addLabel(helper.getTextWidth(), label, true);
        addGroup(helper.getGroupWidth(), Arrays.asList(buttons));
    }

    public ButtonGroup(SizeHelper helper, Enum<?> label, Button[] buttons, boolean isEnabled) {
        super();
        prepareButtons(buttons, isEnabled);
        addLabel(helper.getTextWidth(), label, isEnabled);
        addGroup(helper.getGroupWidth(), Arrays.asList(buttons));
    }

    public ButtonGroup(SizeHelper helper, FilteringButton[] buttons, boolean isEnabled) {
        super();
        List<Button> filteredButtons = getFilteredButtons(buttons);
        prepareButtons(buttons, isEnabled);
        addGroup(helper.getGroupAndTextWidth(), filteredButtons);
    }

    private void addLabel(float width, Enum<?> label, boolean isEnabled) {
        Paragraph title = new Paragraph(getLabelText(label), width, 4, Alignment.RMID);
        if (!isEnabled) {
            title.setColor(Misc.scaleAlpha(title.getColor(), 0.2f));
        }
        getElements().add(title);
    }

    private void addGroup(float width, List<Button> buttons) {
        getElements().add(new DynamicGroup(width - UiConstants.DEFAULT_SPACER, buttons.toArray(new Renderable[] {})));
    }

    private List<Button> getFilteredButtons(FilteringButton[] buttons) {
        List<Button> filteredButtons = new LinkedList<>();
        for (FilteringButton button : buttons) {
            button.setEnabled(false);
            if (button.isVisible()) {
                button.setEnabled(true);
                filteredButtons.add(button);
            }
        }
        return filteredButtons;
    }

    private String getLabelText(Enum<?> label) {
        if (label != null) {
            return L10n.get(label);
        }
        return "";
    }

    private void prepareButtons(Button[] buttons, boolean isEnabled) {
        for (Button button : buttons) {
            button.setTextColor(ColorHelper.basePlayerColor());
            button.setBackgroundColor(ColorHelper.darkPlayerColor());
            button.setEnabled(isEnabled);
        }
        if (isEnabled) {
            return;
        }
        for (Button button : buttons) {
            button.scaleTextColor(0.6f);
            button.scaleBackground(0.5f);
        }
    }
}
