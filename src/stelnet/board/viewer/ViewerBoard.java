package stelnet.board.viewer;

import lombok.Getter;
import stelnet.board.BoardBasePlugin;
import stelnet.board.BoardRenderableInfo;
import stelnet.settings.Modules;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib.RenderableIntelInfo;

@Getter
public class ViewerBoard extends BoardBasePlugin {

    private final String icon = StelnetHelper.getSpriteName("viewer");
    private final RenderableIntelInfo intelInfo = new BoardRenderableInfo(
        L10n.viewer("TITLE"),
        L10n.viewer("DESCRIPTION")
    );
    private final ViewerState renderableState = new ViewerState();
    private final IntelSortTier sortTier = IntelSortTier.TIER_1;
    private final String tag = ModConstants.TAG_VIEWER;

    @Override
    public boolean isHidden() {
        return Modules.MARKET.isHidden();
    }
}
