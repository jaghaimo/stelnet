package stelnet.board.commodity;

import lombok.Getter;
import stelnet.BaseBoard;
import stelnet.BoardInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;

@Getter
public class CommodityBoard extends BaseBoard {

    private final String icon = StelnetHelper.getSpriteName("commodity");
    private final BoardInfo intelInfo = new BoardInfo(
        L10n.get(CommodityL10n.BOARD_TITLE),
        L10n.get(CommodityL10n.BOARD_DESCRIPTION)
    );
    private final CommodityState renderableState = new CommodityState();
    private final String tag = ModConstants.TAG_COMMODITY;

    public void restore() {
        renderableState.getIntelTracker().restore();
    }
}
