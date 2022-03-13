package stelnet.board.viewer;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntelInfo;

@Getter
public class ViewerBoard extends BaseBoard {

    private final String icon = StelnetHelper.getSpriteName("viewer");
    private final RenderableIntelInfo intelInfo = new BoardInfo(
        L10n.get(ViewerL10n.TITLE),
        L10n.get(ViewerL10n.DESCRIPTION)
    );
    private final ViewerState renderableState = new ViewerState();
    private final IntelSortTier sortTier = IntelSortTier.TIER_1;
    private final String tag = ModConstants.TAG_VIEWER;
}
