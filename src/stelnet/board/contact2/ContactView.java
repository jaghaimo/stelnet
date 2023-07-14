package stelnet.board.contact2;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import uilib2.Drawable;
import uilib2.Spacer;
import uilib2.UiConstants;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.SimpleDrawableInfo;
import uilib2.label.HighlightFirst;

@RequiredArgsConstructor
public class ContactView {

    private final ContactModel model;
    private final int filterWidth = 200;

    public DrawableIntelInfo getIntelInfo() {
        final int contactNumber = model.getContactNumber();
        return new SimpleDrawableInfo(
            L10n.contacts("BOARD_TITLE"),
            L10n.contacts("BOARD_DESCRIPTION", contactNumber),
            null,
            new HighlightFirst(String.valueOf(contactNumber))
        );
    }

    public List<Drawable> getDrawables(final float width, final float height) {
        final List<Drawable> drawables = new ArrayList<Drawable>(model.getContacts());
        drawables.add(new Spacer(-UiConstants.SPACER_DEFAULT));
        return drawables;
    }
}
