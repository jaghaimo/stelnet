package stelnet.widget;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import stelnet.util.SectorUtils;
import uilib.Button;
import uilib.RenderableComponent;
import uilib.UiConstants;

public abstract class HeadingWithButtons extends RenderableComponent {

    protected void renderQueryHeading(TooltipMakerAPI tooltip, boolean isEnabled, String headingText) {
        renderHeading(tooltip, isEnabled, "", SectorUtils.getPlayerFaction());
        overlapQueryHeading(tooltip, isEnabled, headingText);
    }

    protected UIComponentAPI renderFirstButton(Button delete, float width, TooltipMakerAPI tooltip) {
        delete.render(tooltip);
        UIComponentAPI deleteComponent = tooltip.getPrev();
        PositionAPI deletePosition = deleteComponent.getPosition();
        deletePosition.setXAlignOffset(width - deletePosition.getWidth() - 5);
        deletePosition.setYAlignOffset(UiConstants.DEFAULT_ROW_HEIGHT);
        return deleteComponent;
    }

    protected UIComponentAPI renderNextButton(
        Button button,
        TooltipMakerAPI tooltip,
        UIComponentAPI previousComponent
    ) {
        return renderNextButton(button, tooltip, previousComponent, 1);
    }

    protected UIComponentAPI renderNextButton(
        Button button,
        TooltipMakerAPI tooltip,
        UIComponentAPI previousComponent,
        float padding
    ) {
        button.render(tooltip);
        UIComponentAPI currentComponent = tooltip.getPrev();
        currentComponent.getPosition().leftOfTop(previousComponent, padding);
        return currentComponent;
    }

    private void renderHeading(TooltipMakerAPI tooltip, boolean isEnabled, String headingText, FactionAPI faction) {
        Color textColor = faction.getBaseUIColor();
        Color backgroundColor = faction.getDarkUIColor();
        if (!isEnabled) {
            textColor = Misc.scaleAlpha(textColor, 0.3f);
            backgroundColor = Misc.scaleAlpha(backgroundColor, 0.2f);
        }
        tooltip.addSectionHeading(headingText, textColor, backgroundColor, Alignment.LMID, 0);
    }

    private void overlapQueryHeading(TooltipMakerAPI tooltip, boolean isEnabled, String heading) {
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
}
