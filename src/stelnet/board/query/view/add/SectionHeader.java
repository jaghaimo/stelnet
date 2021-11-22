package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import uilib.Heading;
import uilib.RenderableComponent;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class SectionHeader extends RenderableComponent {

    private final Heading heading;

    public SectionHeader(float width, Enum<?> label, boolean isEnabled) {
        heading = getHeading(label, isEnabled);
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT + UiConstants.DEFAULT_SPACER * 3));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        heading.render(tooltip);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
    }

    private Heading getHeading(Enum<?> label, boolean isEnabled) {
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
