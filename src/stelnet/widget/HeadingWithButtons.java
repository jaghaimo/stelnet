package stelnet.widget;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.util.SectorUtils;
import uilib.Button;
import uilib.Heading;
import uilib.RenderableComponent;
import uilib.UiConstants;

public abstract class HeadingWithButtons extends RenderableComponent {

    protected void renderQueryHeading(TooltipMakerAPI tooltip, boolean isEnabled, String headingText) {
        renderHeading(tooltip, isEnabled, "", SectorUtils.getPlayerFaction());
        overlapQueryHeading(tooltip, isEnabled, headingText);
    }

    protected void renderHeading(TooltipMakerAPI tooltip, boolean isEnabled, String headingText, FactionAPI faction) {
        Heading heading = new Heading(headingText, faction.getBaseUIColor(), faction.getDarkUIColor());
        if (!isEnabled) {
            heading.setForegroundColor(Misc.scaleAlpha(faction.getBaseUIColor(), 0.3f));
            heading.setBackgroundColor(Misc.scaleAlpha(faction.getDarkUIColor(), 0.2f));
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

    protected UIComponentAPI renderFirst(Button delete, float width, TooltipMakerAPI tooltip) {
        delete.render(tooltip);
        UIComponentAPI deleteComponent = tooltip.getPrev();
        PositionAPI deletePosition = deleteComponent.getPosition();
        deletePosition.setXAlignOffset(width - deletePosition.getWidth() - 5);
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
