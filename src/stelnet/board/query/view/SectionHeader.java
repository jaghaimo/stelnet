package stelnet.board.query.view;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import stelnet.widget.heading.HeadingWithButtons;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class SectionHeader extends HeadingWithButtons {

    private final FilteringButton[] buttons;
    private final String heading;
    private final boolean isEnabled;

    public SectionHeader(final float width, final String label, final boolean isEnabled) {
        this(width, label, isEnabled, null);
    }

    public SectionHeader(
        final float width,
        final String label,
        final boolean isEnabled,
        final FilteringButton[] buttons
    ) {
        this(buttons, label, isEnabled);
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT + UiConstants.DEFAULT_SPACER * 3));
        setWithScroller(false);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        renderQueryHeading(tooltip, isEnabled, heading);
        if (buttons != null) {
            renderButtons(tooltip);
        }
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
    }

    private void renderButtons(final TooltipMakerAPI tooltip) {
        UIComponentAPI currentComponent = renderFirstButton(
            new SelectDeselectButton(L10n.common("NONE"), isEnabled, false, buttons),
            getSize().getWidth(),
            tooltip
        );
        currentComponent =
            renderNextButton(
                new SelectDeselectButton(L10n.common("ALL"), isEnabled, true, buttons),
                tooltip,
                currentComponent
            );
    }
}
