package stelnet.board.contact;

import com.fs.starfarer.api.impl.campaign.ids.Tags;
import lombok.Getter;
import stelnet.board.BoardBasePlugin;
import stelnet.board.BoardRenderableInfo;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntelInfo;

@Getter
public class ContactsBoard extends BoardBasePlugin {

    private final String icon = StelnetHelper.getSpriteName("contacts");
    private final ContactsState renderableState = new ContactsState();
    private final String tag = Tags.INTEL_CONTACTS;

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardRenderableInfo(
            L10n.get(ContactsL10n.BOARD_TITLE),
            L10n.get(ContactsL10n.BOARD_DESCRIPTION, renderableState.getContactNumber())
        );
    }

    @Override
    public boolean isHidden() {
        return renderableState.getContactNumber() == 0;
    }
}
