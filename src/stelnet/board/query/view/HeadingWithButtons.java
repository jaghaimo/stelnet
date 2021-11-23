package stelnet.board.query.view;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import uilib.Button;
import uilib.Heading;
import uilib.RenderableComponent;
import uilib.UiConstants;

public abstract class HeadingWithButtons extends RenderableComponent {

    protected void renderHeading(TooltipMakerAPI tooltip, boolean isEnabled) {
        Heading heading = new Heading("", Misc.getBasePlayerColor(), Misc.getDarkPlayerColor());
        if (!isEnabled) {
            heading.setForegroundColor(Misc.scaleAlpha(Misc.getBasePlayerColor(), 0.3f));
            heading.setBackgroundColor(Misc.scaleAlpha(Misc.getDarkPlayerColor(), 0.2f));
        }
        heading.setAlignment(Alignment.LMID);
        heading.render(tooltip);
    }

    protected void overlapQueryHeading(TooltipMakerAPI tooltip, boolean isEnabled, String heading) {
        tooltip.setParaFontVictor14();
        tooltip.setParaFontColor(Misc.getBrightPlayerColor());
        if (!isEnabled) {
            tooltip.setParaFontColor(Misc.scaleAlpha(Misc.getBrightPlayerColor(), 0.2f));
        }
        tooltip.addPara(heading, 0);
        tooltip.getPrev().getPosition().setYAlignOffset(16);
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setYAlignOffset(-3);
    }

    protected UIComponentAPI renderFirst(Button delete, TooltipMakerAPI tooltip) {
        delete.render(tooltip);
        UIComponentAPI deleteComponent = tooltip.getPrev();
        PositionAPI deletePosition = deleteComponent.getPosition();
        deletePosition.setXAlignOffset(getSize().getWidth() - deletePosition.getWidth() - 5);
        deletePosition.setYAlignOffset(UiConstants.DEFAULT_ROW_HEIGHT);
        return deleteComponent;
    }

    protected UIComponentAPI renderNext(Button button, TooltipMakerAPI tooltip, UIComponentAPI previousComponent) {
        button.render(tooltip);
        UIComponentAPI currentComponent = tooltip.getPrev();
        currentComponent.getPosition().leftOfTop(previousComponent, 1);
        return currentComponent;
    }
}
