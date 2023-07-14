package uilib2.widget;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.label.Label;
import uilib2.label.ParaColored;
import uilib2.label.SectionHeadingColored;

@RequiredArgsConstructor
public class HeaderWithButtons implements Drawable {

    private final Label sectionHeading;
    private final Label para;
    private final LeftButtons leftButtons = new LeftButtons();
    private final RightButtons rightButtons = new RightButtons();

    public HeaderWithButtons(final String paraText, final Color textColor, final Color bgColor) {
        this.sectionHeading = new SectionHeadingColored("", textColor, bgColor, Alignment.LMID, 0);
        this.para = new ParaColored(paraText, textColor, 0);
    }

    public void addLeftButton(final Button button) {
        leftButtons.add(button);
    }

    public void addRightButton(final Button button) {
        if (button != null) {
            rightButtons.add(button);
        }
    }

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final float totalWidth = tooltip.getWidthSoFar();
        drawSectionHeading(tooltip);
        final float widthSoFar = leftButtons.draw(tooltip);
        drawOverlappingPara(tooltip, widthSoFar);
        rightButtons.draw(tooltip, totalWidth);
        return tooltip.getPrev();
    }

    private void drawSectionHeading(final TooltipMakerAPI tooltip) {
        final float heightSoFar = tooltip.getHeightSoFar();
        final UIComponentAPI component = sectionHeading.draw(tooltip);
        final UIComponentAPI spacer = tooltip.addSpacer(0);
        spacer.getPosition().setYAlignOffset(component.getPosition().getHeight());
        tooltip.setHeightSoFar(heightSoFar);
    }

    private void drawOverlappingPara(final TooltipMakerAPI tooltip, final float widthSoFar) {
        tooltip.setParaFontVictor14();
        final UIComponentAPI component = para.draw(tooltip);
        tooltip.setParaFontDefault();
        component.getPosition().setYAlignOffset(-2);
        component.getPosition().setXAlignOffset(widthSoFar);
        final UIComponentAPI spacer = tooltip.addSpacer(0);
        spacer.getPosition().setYAlignOffset(-3);
        spacer.getPosition().setXAlignOffset(-widthSoFar);
    }

    private static class LeftButtons {

        private final List<Button> buttons = new LinkedList<>();

        public void add(final Button button) {
            buttons.add(button);
        }

        public float draw(final TooltipMakerAPI tooltip) {
            if (buttons.isEmpty()) {
                return 0;
            }
            final float heightSoFar = tooltip.getHeightSoFar();
            float widthSoFar = 0;
            float firstButtonWidth = 0;
            UIComponentAPI previousComponent = null;
            for (final Button button : buttons) {
                final UIComponentAPI currentComponent = button.draw(tooltip);
                if (previousComponent != null) {
                    currentComponent.getPosition().rightOfTop(previousComponent, 0);
                    widthSoFar += currentComponent.getPosition().getWidth();
                } else {
                    firstButtonWidth = currentComponent.getPosition().getWidth();
                }
                previousComponent = currentComponent;
                tooltip.setHeightSoFar(heightSoFar);
            }
            final UIComponentAPI spacer = tooltip.addSpacer(0);
            spacer.getPosition().setXAlignOffset(-widthSoFar);
            spacer.getPosition().setYAlignOffset(UiConstants.SECTION_HEADING_HEIGHT);
            return firstButtonWidth + widthSoFar + UiConstants.SPACER_SMALL;
        }
    }

    private static class RightButtons {

        private final List<Button> buttons = new LinkedList<>();

        public void add(final Button button) {
            buttons.add(button);
        }

        public void draw(final TooltipMakerAPI tooltip, float width) {
            if (buttons.isEmpty()) {
                return;
            }
            tooltip.addSpacer(0).getPosition().setYAlignOffset(UiConstants.SECTION_HEADING_HEIGHT);
            final float heightSoFar = tooltip.getHeightSoFar();
            UIComponentAPI previousComponent = null;
            for (final Button button : buttons) {
                final UIComponentAPI currentComponent = button.draw(tooltip);
                final float currentWidth = currentComponent.getPosition().getWidth();
                if (previousComponent == null) {
                    currentComponent.getPosition().setXAlignOffset(tooltip.getWidthSoFar() - currentWidth);
                } else {
                    currentComponent.getPosition().leftOfTop(previousComponent, 0);
                }
                width -= currentWidth;
                previousComponent = currentComponent;
                tooltip.setHeightSoFar(heightSoFar);
            }
            tooltip.addSpacer(0).getPosition().setXAlignOffset(-width);
        }
    }
}
