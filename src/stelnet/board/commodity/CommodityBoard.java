package stelnet.board.commodity;

import lombok.Getter;
import stelnet.board.BoardBasePlugin;
import stelnet.board.BoardRenderableInfo;
import stelnet.settings.Modules;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;

@Getter
public class CommodityBoard extends BoardBasePlugin {

    private final String icon = StelnetHelper.getSpriteName("commodity");
    private final BoardRenderableInfo intelInfo = new BoardRenderableInfo(
        L10n.get(CommodityL10n.BOARD_TITLE),
        L10n.get(CommodityL10n.BOARD_DESCRIPTION)
    );
    private final CommodityState renderableState = new CommodityState();
    private final String tag = ModConstants.TAG_COMMODITY;

    public void restore() {
        renderableState.getIntelTracker().restore();
    }

    @Override
    public boolean isHidden() {
        return Modules.COMMODITIES.isHidden();
    }
}
