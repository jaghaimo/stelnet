package stelnet.board.contact;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.ModConstants;
import stelnet.util.SettingsUtils;
import uilib.RenderableIntelInfo;

@Getter
public class ContactBoard extends BaseBoard {

    private final String icon = SettingsUtils.getSpriteName("query");
    private final ContactState renderableState = new ContactState();
    private final String tag = ModConstants.TAG_CONTACT;

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo("Contacts", "Managing " + renderableState.getContacts());
    }
}
