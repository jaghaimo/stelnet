package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.util.L10n;
import stelnet.widget.HeadingWithButtons;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class SectionHeader extends HeadingWithButtons {

    private final FilteringButton[] buttons;
    private final String heading;
    private final boolean isEnabled;

    public SectionHeader(float width, Enum<?> label, boolean isEnabled) {
        this(width, label, isEnabled, null);
    }

    public SectionHeader(float width, Enum<?> label, boolean isEnabled, FilteringButton[] buttons) {
        this(buttons, L10n.get(label), isEnabled);
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT + UiConstants.DEFAULT_SPACER * 3));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        renderQueryHeading(tooltip, isEnabled, heading);
        if (buttons != null) {
            UIComponentAPI first = renderFirst(
                new SelectDeselectButton(CommonL10n.NONE, isEnabled, false, buttons),
                getSize().getWidth(),
                tooltip
            );
            renderNext(new SelectDeselectButton(CommonL10n.ALL, isEnabled, true, buttons), tooltip, first);
        }
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
    }
}
