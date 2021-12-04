package stelnet.board.contact;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SettingsUtils;
import uilib.RenderableIntelInfo;

@Getter
public class ContactsBoard extends BaseBoard {

    private final String icon = SettingsUtils.getSpriteName("contacts");
    private final ContactsState renderableState = new ContactsState();
    private final String tag = ModConstants.TAG_CONTACTS;

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(
            L10n.get(ContactsL10n.BOARD_TITLE),
            L10n.get(ContactsL10n.BOARD_DESCRIPTION, renderableState.getContactNumber())
        );
    }

    @Override
    public boolean isHidden() {
        return renderableState.getContactNumber() == 0;
    }
}
