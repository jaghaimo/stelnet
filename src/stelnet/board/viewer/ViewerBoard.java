package stelnet.board.viewer;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.SettingsUtils;
import uilib.RenderableIntelInfo;
import uilib.RenderableState;

@Getter
public class ViewerBoard extends BaseBoard {

    private final ViewerState state = new ViewerState();
    private final IntelSortTier sortTier = IntelSortTier.TIER_1;

    @Override
    public String getIcon() {
        return SettingsUtils.getSpriteName("viewer");
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new BoardInfo(L10n.get(ViewerL10n.TITLE), L10n.get(ViewerL10n.DESCRIPTION));
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return ModConstants.TAG_MARKET;
    }
}
