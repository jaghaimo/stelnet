package stelnet.board.trade;

import lombok.Getter;
import stelnet.board.BoardBasePlugin;
import stelnet.board.BoardRenderableInfo;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;

@Getter
public class TradeBoard extends BoardBasePlugin {

    private final String icon = StelnetHelper.getSpriteName("trade");
    private final BoardRenderableInfo intelInfo = new BoardRenderableInfo(
        L10n.get(TradeL10n.BOARD_TITLE),
        L10n.get(TradeL10n.BOARD_DESCRIPTION)
    );
    private final TradeState renderableState = new TradeState();
    private final IntelSortTier sortTier = IntelSortTier.TIER_1;
    private final String tag = ModConstants.TAG_COMMODITY;
}
