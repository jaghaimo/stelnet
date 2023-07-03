package stelnet.board.query.view;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import uilib.Button;
import uilib.ColorHelper;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.UiConstants;

public class ButtonGroup extends HorizontalViewContainer {

    public ButtonGroup(final SizeHelper helper, final String label, final Button[] buttons) {
        super();
        addLabel(helper.getTextWidth(), label, true);
        addGroup(helper.getGroupWidth(), Arrays.asList(buttons));
    }

    public ButtonGroup(final SizeHelper helper, final String label, final Button[] buttons, final boolean isEnabled) {
        super();
        prepareButtons(buttons, isEnabled);
        addLabel(helper.getTextWidth(), label, isEnabled);
        addGroup(helper.getGroupWidth(), Arrays.asList(buttons));
    }

    public ButtonGroup(final SizeHelper helper, final FilteringButton[] buttons, final boolean isEnabled) {
        super();
        final List<Button> filteredButtons = getFilteredButtons(buttons);
        prepareButtons(buttons, isEnabled);
        addGroup(helper.getGroupAndTextWidth(), filteredButtons);
    }

    private void addLabel(final float width, final String label, final boolean isEnabled) {
        final Paragraph title = new Paragraph(label, width, 4, Alignment.RMID);
        if (!isEnabled) {
            title.setColor(Misc.scaleAlpha(title.getColor(), 0.2f));
        }
        getElements().add(title);
    }

    private void addGroup(final float width, final List<Button> buttons) {
        getElements().add(new DynamicGroup(width - UiConstants.DEFAULT_SPACER, buttons.toArray(new Renderable[] {})));
    }

    private List<Button> getFilteredButtons(final FilteringButton[] buttons) {
        final List<Button> filteredButtons = new LinkedList<>();
        for (final FilteringButton button : buttons) {
            button.setEnabled(false);
            if (button.isVisible()) {
                button.setEnabled(true);
                filteredButtons.add(button);
            }
        }
        return filteredButtons;
    }

    private void prepareButtons(final Button[] buttons, final boolean isEnabled) {
        for (final Button button : buttons) {
            button.setTextColor(ColorHelper.basePlayerColor());
            button.setBackgroundColor(ColorHelper.darkPlayerColor());
            button.setEnabled(isEnabled);
        }
        if (isEnabled) {
            return;
        }
        for (final Button button : buttons) {
            button.scaleTextColor(0.6f);
            button.scaleBackground(0.5f);
        }
    }
}
