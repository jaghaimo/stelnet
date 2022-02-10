package stelnet.board.query.view;

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

    private final FilteringButton[] filterButtons;
    private final FilteringButton filterLogicButton;
    private final String heading;
    private final boolean isEnabled;

    public SectionHeader(float width, Enum<?> label, boolean isEnabled) {
        this(width, label, isEnabled, null, null);
    }

    public SectionHeader(float width, Enum<?> label, boolean isEnabled, FilteringButton[] filterButtons) {
        this(width, label, isEnabled, filterButtons, null);
    }

    public SectionHeader(
        float width,
        Enum<?> label,
        boolean isEnabled,
        FilteringButton[] filterButtons,
        FilteringButton filterLogicButton
    ) {
        this(filterButtons, filterLogicButton, L10n.get(label), isEnabled);
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT + UiConstants.DEFAULT_SPACER * 3));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        renderQueryHeading(tooltip, isEnabled, heading);
        if (filterButtons != null) {
            renderButtons(tooltip);
        }
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
    }

    private void renderButtons(TooltipMakerAPI tooltip) {
        UIComponentAPI currentComponent = renderFirstButton(
            new SelectDeselectButton(CommonL10n.NONE, isEnabled, false, filterButtons),
            getSize().getWidth(),
            tooltip
        );
        currentComponent =
            renderNextButton(
                new SelectDeselectButton(CommonL10n.ALL, isEnabled, true, filterButtons),
                tooltip,
                currentComponent
            );
        if (filterLogicButton != null) {
            renderNextButton(filterLogicButton, tooltip, currentComponent);
        }
    }
}
