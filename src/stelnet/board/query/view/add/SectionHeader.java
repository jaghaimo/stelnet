package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.util.L10n;
import uilib.Heading;
import uilib.RenderableComponent;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class SectionHeader extends RenderableComponent {

    private final FilteringButton[] buttons;
    private final Heading heading;
    private final boolean isEnabled;

    public SectionHeader(float width, Enum<?> label, boolean isEnabled) {
        this(width, label, isEnabled, null);
    }

    public SectionHeader(float width, Enum<?> label, boolean isEnabled, FilteringButton[] buttons) {
        this(buttons, getHeading(label, isEnabled), isEnabled);
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT + UiConstants.DEFAULT_SPACER * 3));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        heading.render(tooltip);
        if (buttons != null) {
            tooltip.setButtonFontVictor10();
            renderButtons(tooltip);
        }
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
    }

    private void renderButtons(TooltipMakerAPI tooltip) {
        SelectDeselectButton noneButton = new SelectDeselectButton(CommonL10n.NONE, isEnabled, false, buttons);
        noneButton.render(tooltip);
        UIComponentAPI noneComponent = tooltip.getPrev();
        PositionAPI nonePosition = noneComponent.getPosition();
        nonePosition.setXAlignOffset(getSize().getWidth() - nonePosition.getWidth() - 5);
        nonePosition.setYAlignOffset(UiConstants.DEFAULT_ROW_HEIGHT);
        SelectDeselectButton allButton = new SelectDeselectButton(CommonL10n.ALL, isEnabled, true, buttons);
        allButton.render(tooltip);
        tooltip.getPrev().getPosition().leftOfTop(noneComponent, 1);
    }

    private static Heading getHeading(Enum<?> label, boolean isEnabled) {
        String headingTitle = " " + L10n.get(label);
        Heading heading = new Heading(headingTitle, Misc.getBasePlayerColor(), Misc.getDarkPlayerColor());
        heading.setAlignment(Alignment.LMID);
        if (!isEnabled) {
            heading.setForegroundColor(Misc.scaleAlpha(Misc.getBasePlayerColor(), 0.3f));
            heading.setBackgroundColor(Misc.scaleAlpha(Misc.getDarkPlayerColor(), 0.2f));
        }
        return heading;
    }
}
