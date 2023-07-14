package stelnet.board.contact2;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import java.awt.*;
import lombok.RequiredArgsConstructor;
import uilib2.Drawable;
import uilib2.Spacer;
import uilib2.UiConstants;
import uilib2.label.ParaBasic;
import uilib2.widget.HeaderWithButtons;

@RequiredArgsConstructor
public class DrawableContact implements Drawable {

    private final Contact contact;
    private final ButtonFactory factory = new ButtonFactory();

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final IntelInfoPlugin intel = contact.getIntel();
        final Color base = contact.getBaseColor();
        final Color dark = contact.getDarkColor();
        final HeaderWithButtons headerWithButtons = new HeaderWithButtons(
            contact.getName(),
            contact.getBaseColor(),
            contact.getDarkColor()
        );
        headerWithButtons.addLeftButton(factory.getLeft(intel, base, dark));
        headerWithButtons.addLeftButton(factory.getNumber(base, dark));
        headerWithButtons.addLeftButton(factory.getRight(intel, base, dark));
        headerWithButtons.addRightButton(factory.getCall(intel, base, dark));
        headerWithButtons.addRightButton(factory.getShow(intel, base, dark));
        headerWithButtons.draw(tooltip);
        new ParaBasic(contact.getMissionTypeText() + contact.getLocationText(), UiConstants.SPACER_SMALL).draw(tooltip);
        new Spacer(UiConstants.SPACER_LARGE).draw(tooltip);
        return null;
    }
}
