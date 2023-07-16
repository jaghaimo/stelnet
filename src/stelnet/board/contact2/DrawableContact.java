package stelnet.board.contact2;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
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
    private final ButtonFactory factory = new ButtonFactory();

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final IntelInfoPlugin intel = contact.getIntel();
        final Color base = contact.getPerson().getFaction().getBaseUIColor();
        final Color dark = contact.getPerson().getFaction().getDarkUIColor();
        final HeaderWithButtons headerWithButtons = new HeaderWithButtons(contact.getTitle(), base, dark);
        headerWithButtons.addLeftButton(factory.getLeft(intel, base, dark));
        headerWithButtons.addLeftButton(factory.getNumber(base, dark));
        headerWithButtons.addLeftButton(factory.getRight(intel, base, dark));
        headerWithButtons.addRightButton(factory.getCall(intel, base, dark));
        headerWithButtons.addRightButton(factory.getShow(intel, base, dark));
        headerWithButtons.draw(tooltip);
        final TooltipMakerAPI innerTooltip = tooltip.beginImageWithText(contact.getPerson().getPortraitSprite(), 64);
        innerTooltip.addPara(contact.getMissionTypeText() + contact.getLocationText(), 1);
        tooltip.addImageWithText(UiConstants.SPACER_SMALL);
        tooltip.addSpacer(UiConstants.SPACER_LARGE);
        return null;
    }
}
