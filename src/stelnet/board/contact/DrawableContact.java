package stelnet.board.contact;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import java.awt.*;
import lombok.RequiredArgsConstructor;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.widget.HeaderWithButtons;

@RequiredArgsConstructor
public class DrawableContact implements Drawable {

    private final Contact contact;
    private final ButtonFactory factory;

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final Color base = contact.getPerson().getFaction().getBaseUIColor();
        final Color dark = contact.getPerson().getFaction().getDarkUIColor();
        final HeaderWithButtons headerWithButtons = new HeaderWithButtons(contact.getTitle(), base, dark);
        headerWithButtons.addLeftButton(factory.getLeft(base, dark));
        headerWithButtons.addLeftButton(factory.getNumber(base, dark));
        headerWithButtons.addLeftButton(factory.getRight(base, dark));
        headerWithButtons.addRightButton(factory.getCall(base, dark));
        headerWithButtons.addRightButton(factory.getShow(base, dark));
        headerWithButtons.draw(tooltip);
        final TooltipMakerAPI innerTooltip = tooltip.beginImageWithText(contact.getPerson().getPortraitSprite(), 64);
        innerTooltip.addPara(contact.getMissionTypeText() + contact.getLocationText(), 1);
        tooltip.addImageWithText(UiConstants.SPACER_SMALL);
        tooltip.addSpacer(UiConstants.SPACER_LARGE);
        return null;
    }
}
