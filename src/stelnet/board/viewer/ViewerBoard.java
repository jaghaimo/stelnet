package stelnet.board.viewer;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.SettingsUtils;
import stelnet.util.TagConstants;
import uilib.RenderableState;

@Getter
public class ViewerBoard extends BaseBoard {

    private final ViewerState state = new ViewerState();

    @Override
    public String getIcon() {
        return SettingsUtils.getSpriteName("viewer");
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_1;
    }

    @Override
    protected BoardInfo getBoardInfo() {
        return new BoardInfo(L10n.get("marketViewTitle"), L10n.get("marketViewDescription"));
    }

    @Override
    protected RenderableState getRenderableState() {
        return state;
    }

    @Override
    protected String getTag() {
        return TagConstants.MARKET;
    }
}
