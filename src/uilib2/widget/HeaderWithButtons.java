package uilib2.widget;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uilib.UiConstants;
import uilib2.Drawable;
import uilib2.button.Button;
import uilib2.label.Label;
import uilib2.label.ParaColored;
import uilib2.label.SectionHeadingColored;

@RequiredArgsConstructor
public class HeaderWithButtons implements Drawable {

    private final Label sectionHeading;
    private final Label para;
    private final Button firstButton;

    @Getter
    private final List<Button> otherButtons = new LinkedList<>();

    public HeaderWithButtons(String paraText, Color textColor, Color bgColor, Button firstButton) {
        this.sectionHeading = new SectionHeadingColored("", textColor, bgColor, Alignment.LMID, 0);
        this.para = new ParaColored(paraText, textColor, 0);
        this.firstButton = firstButton;
    }

    @Override
    public UIComponentAPI draw(TooltipMakerAPI tooltip) {
        float width = tooltip.getWidthSoFar();
        drawSectionHeading(tooltip);
        drawOverlappingPara(tooltip);
        if (firstButton == null) {
            return tooltip.getPrev();
        }
        UIComponentAPI currentComponent = drawFirstButton(tooltip);
        width -= currentComponent.getPosition().getWidth();
        for (Button otherButton : otherButtons) {
            currentComponent = drawOtherButton(tooltip, otherButton, currentComponent);
            width -= currentComponent.getPosition().getWidth();
        }
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setXAlignOffset(-width);
        return tooltip.getPrev();
    }

    private void drawSectionHeading(TooltipMakerAPI tooltip) {
        sectionHeading.draw(tooltip);
    }

    private void drawOverlappingPara(TooltipMakerAPI tooltip) {
        tooltip.setParaFontVictor14();
        para.draw(tooltip);
        tooltip.setParaFontDefault();
        tooltip.getPrev().getPosition().setYAlignOffset(16);
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setYAlignOffset(-3);
    }

    private UIComponentAPI drawFirstButton(TooltipMakerAPI tooltip) {
        firstButton.draw(tooltip);
        UIComponentAPI component = tooltip.getPrev();
        PositionAPI componentPosition = component.getPosition();
        componentPosition.setXAlignOffset(tooltip.getWidthSoFar() - componentPosition.getWidth());
        componentPosition.setYAlignOffset(UiConstants.DEFAULT_ROW_HEIGHT);
        return component;
    }

    private UIComponentAPI drawOtherButton(TooltipMakerAPI tooltip, Button button, UIComponentAPI previousComponent) {
        button.draw(tooltip);
        UIComponentAPI currentComponent = tooltip.getPrev();
        currentComponent.getPosition().leftOfTop(previousComponent, 0);
        return currentComponent;
    }
}
